const API_URL = 'http://localhost:8080'

export async function getRecommendations({
  mobileUnits,
  maxTravelTime,
  priorityProfile,
}) {
  const params = new URLSearchParams({
    mobileUnits: String(mobileUnits),
    travelLimit: String(maxTravelTime),
    priority: priorityProfile,
  })

  const response = await fetch(
    `${API_URL}/api/recommendations?${params.toString()}`
  )

  if (!response.ok) {
    throw new Error(`Backend error: ${response.status}`)
  }

  return await response.json()
}