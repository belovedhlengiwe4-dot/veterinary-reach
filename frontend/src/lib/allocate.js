function haversineKm(lat1, lon1, lat2, lon2) {
  const R = 6371
  const toRad = (deg) => (deg * Math.PI) / 180

  const dLat = toRad(lat2 - lat1)
  const dLon = toRad(lon2 - lon1)

  const a =
    Math.sin(dLat / 2) ** 2 +
    Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * Math.sin(dLon / 2) ** 2

  return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
}

const RISK_WEIGHT = { low: 1, medium: 1.5, high: 2 }
const DISEASE_RISK_WEIGHT = { low: 1, medium: 2, high: 4 }

/**
 * Returns a weighted score for a single farm under the given priority profile.
 * Higher scores make a farm more attractive to cover first.
 */
function scoreForProfile(farm, priorityProfile) {
  switch (priorityProfile) {
    case "max-coverage":
      return farm.livestock

    case "disease-risk":
      return farm.livestock * (DISEASE_RISK_WEIGHT[farm.diseaseRisk] ?? 1)

    case "farmer-coverage":
      return farm.farmers * 10

    case "min-travel":
      // TODO: this only scores by livestock for now. To truly prefer
      // min-travel, candidates with close scores should be tie-broken by
      // distance from a depot/previous stop — needs more thought on how
      // to combine that with coverage scoring.
      return farm.livestock

    case "balanced":
    default:
      return farm.livestock + farm.farmers * 10 + (RISK_WEIGHT[farm.diseaseRisk] ?? 1)
  }
}

function coverageForPoint(candidate, farms, maxRadiusKm, priorityProfile = "balanced") {
  const coveredFarms = farms.filter(
    (farm) => haversineKm(candidate.lat, candidate.lng, farm.lat, farm.lng) <= maxRadiusKm
  )

  const totalScore = coveredFarms.reduce((sum, farm) => sum + scoreForProfile(farm, priorityProfile), 0)
  const totalLivestock = coveredFarms.reduce((sum, farm) => sum + farm.livestock, 0)
  const totalFarmers = coveredFarms.reduce((sum, farm) => sum + farm.farmers, 0)

  return { coveredFarms, totalScore, totalLivestock, totalFarmers }
}

function allocateTeams(farms, numTeams, maxRadiusKm, priorityProfile = "balanced") {
  let uncovered = [...farms]
  const deployments = []

  for (let i = 0; i < numTeams; i++) {

    if(uncovered.length === 0) break

    let bestCandidate = null
    let bestResult = { coveredFarms: [], totalScore: -1, totalLivestock: 0, totalFarmers: 0 }

    for (const candidate of farms) {
      const result = coverageForPoint(candidate, uncovered, maxRadiusKm, priorityProfile)
      if (result.totalScore > bestResult.totalScore) {
        bestCandidate = candidate
        bestResult = result
      }
    }

    if (!bestCandidate) break

    deployments.push({
      location: bestCandidate,
      coveredFarms: bestResult.coveredFarms,
      livestockCovered: bestResult.totalLivestock,
      farmersCovered: bestResult.totalFarmers,
      score: bestResult.totalScore,
    })

    uncovered = uncovered.filter((farm) => !bestResult.coveredFarms.includes(farm))
  }

  return deployments
}

const EXPLANATION_WORDING = {
  balanced: { livestock: "high animal demand", farmers: "many farmer households depending on this area", risk: "elevated disease risk" },
  "max-coverage": { livestock: "high animal demand", farmers: "broad farmer reach", risk: "elevated disease risk" },
  "disease-risk": { livestock: "high livestock density", farmers: "many exposed farmer households", risk: "high disease risk" },
  "farmer-coverage": { livestock: "high livestock density", farmers: "large farmer population", risk: "elevated disease risk" },
  "min-travel": { livestock: "high animal demand", farmers: "many farmer households", risk: "elevated disease risk" },
}

/**
 * Produces a short plain-language reason for why a deployment's location was
 * chosen, based on whichever factor (livestock, farmers, disease risk) is
 * most prominent among its covered farms, worded to match the priority profile.
 */
function explainDeployment(deployment, priorityProfile = "balanced") {
  const farms = deployment?.coveredFarms ?? []
  if (farms.length === 0) return "No farms covered"

  const avgLivestock = farms.reduce((sum, farm) => sum + farm.livestock, 0) / farms.length
  const avgFarmers = farms.reduce((sum, farm) => sum + farm.farmers, 0) / farms.length
  const avgRiskWeight =
    farms.reduce((sum, farm) => sum + (DISEASE_RISK_WEIGHT[farm.diseaseRisk] ?? 1), 0) / farms.length

  // Normalize each metric against a rough "typical farm" baseline so livestock,
  // farmers, and disease risk can be ranked against each other on one scale.
  const factors = [
    { key: "livestock", value: avgLivestock / 80 },
    { key: "farmers", value: avgFarmers / 6 },
    { key: "risk", value: avgRiskWeight / 1.5 },
  ].sort((a, b) => b.value - a.value)

  const wording = EXPLANATION_WORDING[priorityProfile] ?? EXPLANATION_WORDING.balanced
  const [topFactor, secondFactor] = factors
  const topReason = wording[topFactor.key]
  const capitalized = topReason.charAt(0).toUpperCase() + topReason.slice(1)

  if (priorityProfile === "min-travel") {
    // No real travel-time data is available yet, so "access" is inferred from
    // farm clustering rather than measured directly.
    return `${capitalized} + poor veterinary access`
  }

  if (secondFactor && secondFactor.value > 0.5) {
    return `${capitalized} + ${wording[secondFactor.key]}`
  }

  return capitalized
}

/**
 * naiveBaseline
 * -------------
 * Simulates the "obvious but wrong" approach: send vets to the N farms
 * with the most livestock, regardless of geography. This is what most
 * people would do without an algorithm — used purely as a comparison
 * point to show the value of allocateTeams.
 *
 * @param {object[]} farms — full list of farms
 * @param {number} numTeams — number of teams available
 * @param {number} maxRadiusKm — service radius per team
 * @returns {{ anchors: object[], coveredFarms: object[], livestockCovered: number, farmersCovered: number, farmsCovered: number }}
 */
function naiveBaseline(farms, numTeams, maxRadiusKm) {
  const sortedByLivestock = [...farms].sort((a, b) => b.livestock - a.livestock)
  const anchors = sortedByLivestock.slice(0, numTeams)

  const coveredIds = new Set()
  anchors.forEach((anchor) => {
    farms.forEach((farm) => {
      if (haversineKm(anchor.lat, anchor.lng, farm.lat, farm.lng) <= maxRadiusKm) {
        coveredIds.add(farm.id)
      }
    })
  })

  const coveredFarms = farms.filter((farm) => coveredIds.has(farm.id))
  return {
    anchors,
    coveredFarms,
    livestockCovered: coveredFarms.reduce((sum, farm) => sum + farm.livestock, 0),
    farmersCovered: coveredFarms.reduce((sum, farm) => sum + farm.farmers, 0),
    farmsCovered: coveredFarms.length,
  }
}

// Assumes an average rural travel speed of 40 km/h — a simulated approximation,
// since real road-speed data isn't available.
function travelTimeToKm(minutes) {
  const avgSpeedKmh = 40
  return (minutes / 60) * avgSpeedKmh
}

/**
 * Averages the distance (in km) from each covered farm to its nearest
 * reference point (a deployment location or baseline anchor) — a proxy for
 * how much ground farmers/teams must cover to reach that point.
 */
function calculateTravelBurden(coveredFarms, referencePoints) {
  if (coveredFarms.length === 0 || referencePoints.length === 0) return 0

  const totalKm = coveredFarms.reduce((sum, farm) => {
    const nearestKm = Math.min(
      ...referencePoints.map((point) => haversineKm(point.lat, point.lng, farm.lat, farm.lng))
    )
    return sum + nearestKm
  }, 0)

  return Number((totalKm / coveredFarms.length).toFixed(1))
}

export {
  haversineKm,
  coverageForPoint,
  allocateTeams,
  naiveBaseline,
  travelTimeToKm,
  scoreForProfile,
  explainDeployment,
  calculateTravelBurden,
}