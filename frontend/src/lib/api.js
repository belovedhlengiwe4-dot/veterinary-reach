import { mockFarms } from '../data/mockFarms'
import { allocateTeams, naiveBaseline, explainDeployment, calculateTravelBurden, travelTimeToKm } from './allocate'

/**
 * getRecommendations
 * -------------------
 * Runs the allocation algorithm and packages the result with everything
 * the UI needs: per-location recommendations, and a baseline comparison.
 *
 * TODO (integration day): replace the body of this function with a
 * fetch('/api/recommendations') call — the shape returned below is the
 * agreed API contract, so nothing calling this function needs to change.
 */
export async function getRecommendations({ mobileUnits, maxTravelTime, priorityProfile }) {
  const totalLivestock = mockFarms.reduce((sum, farm) => sum + farm.livestock, 0)

  const maxRadiusKm = travelTimeToKm(maxTravelTime)

  const deployments = allocateTeams(mockFarms, mobileUnits, maxRadiusKm, priorityProfile)

  // calculateTravelBurden returns km; convert to minutes at the same 40 km/h
  // assumption travelTimeToKm uses, just inverted.
  const avgSpeedKmh = 40
  const kmToMinutes = (km) => Number(((km / avgSpeedKmh) * 60).toFixed(1))

  const recommendations = deployments.map((deployment) => ({
    location: deployment.location.name,
    latitude: deployment.location.lat,
    longitude: deployment.location.lng,
    reason: explainDeployment(deployment, priorityProfile),
    animalsReached: deployment.livestockCovered,
    farmersReached: deployment.farmersCovered,
    travelTime: kmToMinutes(calculateTravelBurden(deployment.coveredFarms, [deployment.location])),
    diseaseRisk: deployment.location.diseaseRisk,
  }))

  const coveredFarmIds = new Set(
    deployments.flatMap((deployment) => deployment.coveredFarms.map((farm) => farm.id))
  )
  const vetreachCoveredFarms = mockFarms.filter((farm) => coveredFarmIds.has(farm.id))
  const vetreachLivestock = vetreachCoveredFarms.reduce((sum, farm) => sum + farm.livestock, 0)
  const vetreachFarmers = vetreachCoveredFarms.reduce((sum, farm) => sum + farm.farmers, 0)
  const vetreachCoverage = totalLivestock > 0
    ? Number(((vetreachLivestock / totalLivestock) * 100).toFixed(1))
    : 0

  const baseline = naiveBaseline(mockFarms, mobileUnits, maxRadiusKm)
  const baselineCoverage = totalLivestock > 0
    ? Number(((baseline.livestockCovered / totalLivestock) * 100).toFixed(1))
    : 0

  return {
    recommendations,
    comparison: {
      vetreach: {
        coverage: vetreachCoverage,
        animalsReached: vetreachLivestock,
        farmersReached: vetreachFarmers,
        travelBurden: calculateTravelBurden(
          vetreachCoveredFarms,
          deployments.map((deployment) => deployment.location)
        ),
      },
      baseline: {
        coverage: baselineCoverage,
        animalsReached: baseline.livestockCovered,
        farmersReached: baseline.farmersCovered,
        travelBurden: calculateTravelBurden(baseline.coveredFarms, baseline.anchors),
      },
    },
  }
}
