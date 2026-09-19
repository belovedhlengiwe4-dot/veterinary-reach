import { useState } from 'react'
import { getRecommendations } from './lib/api'
import RecommendationMap from './components/RecommendationMap'

function App() {

  const [mobileUnits, setMobileUnits] = useState(2)
  const [maxTravelTime, setMaxTravelTime] = useState(60)
  const [priorityProfile, setPriorityProfile] = useState('balanced')
  const [result, setResult] = useState(null)
  const [isLoading, setIsLoading] = useState(false)
  
  const handleSubmit = async (event) => {
  event.preventDefault()
    setIsLoading(true)
    setResult(null)

    try {
      const data = await getRecommendations({
        mobileUnits,
        maxTravelTime,
        priorityProfile,
      })

      console.log('Backend response:', data)

      setResult(data)
    } catch (error) {
      console.error('Failed to get recommendations:', error)
    } finally {
      setIsLoading(false)
    }
  }
  const sectionHeadingClass = "font-['Zilla_Slab'] text-center uppercase text-sm font-bold tracking-wide text-[#0F5C57] mb-6"

  return (
    <div className="min-h-screen bg-[#FAFAF8] text-[#0F5C57]">
      <header className="bg-[#0F5C57] text-white border-b-4 border-[#FF6B5E] px-6 py-4">
        <h1 className="font-['Zilla_Slab'] text-xl font-bold text-white">Veterinary Reach</h1>
        <p className="text-sm text-[#BFE3DF]">Vhembe District, Limpopo</p>
      </header>

      <main className="max-w-3xl mx-auto p-6 space-y-6">
        <section aria-labelledby="controls-heading" className="bg-[#F3FAF9] border border-[#CDE7E4] rounded-lg p-6">
          <h2 id="controls-heading" className={sectionHeadingClass}>Resource allocation</h2>
          <form onSubmit={handleSubmit}>
            <fieldset className="grid grid-cols-3 gap-4">
              <legend className="sr-only">Allocation settings</legend>

              <div>
                <label htmlFor="mobileUnits" className="block text-sm text-[#5B7E7A] mb-1">Mobile units</label>
                <input
                  type="number"
                  id="mobileUnits"
                  name="mobileUnits"
                  value={mobileUnits}
                  onChange={(e)=>setMobileUnits(Number(e.target.value))}
                  className="w-full bg-white border border-[#CDE7E4] rounded px-3 py-2"
                />
              </div>

              <div>
                <label htmlFor="maxTravelTime" className="block text-sm text-[#5B7E7A] mb-1">Travel limit</label>
                <select
                  id="maxTravelTime"
                  name="maxTravelTime"
                  value={maxTravelTime}
                  onChange={(e) => setMaxTravelTime(Number(e.target.value))}
                  className="w-full bg-white border border-[#CDE7E4] rounded px-3 py-2"
                >
                  <option value={30}>30</option>
                  <option value={45}>45</option>
                  <option value={60}>60</option>
                  <option value={90}>90</option>
                  <option value={120}>120</option>
                </select>
              </div>

              <div>
                <label htmlFor="priorityProfile" className="block text-sm text-[#5B7E7A] mb-1">Priority</label>
                <select
                  id="priorityProfile"
                  name="priorityProfile"
                  value={priorityProfile}
                  onChange={(e) => setPriorityProfile(e.target.value)}
                  className="w-full bg-white border border-[#CDE7E4] rounded px-3 py-2"
                >
                  <option value="balanced">Balanced</option>
                  <option value="max-coverage">Maximum animal coverage</option>
                  <option value="disease-risk">Disease risk</option>
                  <option value="farmer-coverage">Farmer coverage</option>
                  <option value="min-travel">Minimum travel</option>
                </select>
              </div>
            </fieldset>

            <button
              type="submit"
              disabled={isLoading}
              className="w-full mt-4 bg-[#FF6B5E] text-white font-bold rounded py-3 text-center hover:bg-[#e85c50]"
            >
              {isLoading ? "Running..." : "Calculate best deployment location"}
            </button>
          </form>
        </section>

        <section aria-labelledby="map-heading" className="bg-[#F3FAF9] border border-[#CDE7E4] rounded-lg p-6">
          <h2 id="map-heading" className={sectionHeadingClass}>Deployment map</h2>
          <div className="h-64 bg-[#CDE7E4] rounded flex items-center justify-center text-[#5B7E7A] text-sm">
              <RecommendationMap
            recommendations={result ? result.recommendations : []}
          />
          </div>
          <div className="mt-3 flex items-center justify-center gap-6 text-xs text-[#5B7E7A]">
            <span><span className="text-[#0F5C57]">●</span> Vet facility</span>
            <span><span className="text-[#FF6B5E]">★</span> Recommended deployment</span>
          </div>
        </section>

        <section aria-labelledby="results-heading" className="bg-[#F3FAF9] border border-[#CDE7E4] rounded-lg p-6">
          <h2 id="results-heading" className={sectionHeadingClass}>Recommended deployment</h2>
          {result === null ? (
            <p className="text-sm text-[#5B7E7A]">Run an allocation to see recommended locations.</p>
          ) : (
            <div className={result.recommendations.length === 1 ? "flex justify-center" : "grid grid-cols-1 sm:grid-cols-3 gap-4"}>
              {result.recommendations.map((recommendation, index) => (
                <div
                  key={index}
                  className={`border border-[#CDE7E4] rounded-lg bg-white p-4 ${
                    result.recommendations.length === 1 ? "w-full sm:w-1/3" : ""
                  }`}
                >
                  <p className="font-['Zilla_Slab'] font-bold text-[#0F5C57]">#{index + 1} {recommendation.location}</p>
                  <p className="italic text-[#FF6B5E] mt-1">{recommendation.reason}</p>
                  <p className="text-sm text-[#5B7E7A] mt-2">
                    {` ${Math.round(recommendation.travelTime)} min · ${recommendation.diseaseRisk} risk`}
                  </p>
                </div>
              ))}
            </div>
          )}
        </section>

        <section aria-labelledby="comparison-heading" className="bg-[#F3FAF9] border border-[#CDE7E4] rounded-lg p-6">
          <h2 id="comparison-heading" className={sectionHeadingClass}>Veterinary Reach vs baseline</h2>
          {result === null ? (
            <p className="text-sm text-[#5B7E7A]">Run an allocation to see the comparison.</p>
          ) : (
            <div className="space-y-4">
              <div>
                <p className="text-sm text-[#5B7E7A]">Livestock coverage</p>
                <p className="font-['Zilla_Slab'] text-4xl font-bold text-[#0F5C57]">{result.comparison.vetreach.coverage}%</p>
                <p className="text-sm text-[#FF6B5E]">
                  up from {result.comparison.baseline.coverage}% with the baseline approach
                </p>
              </div>

              <div className="grid grid-cols-3 gap-3">
                <div className="border border-[#CDE7E4] rounded bg-white p-3 text-center">
                  <p className="font-bold text-lg text-[#0F5C57]">{result.comparison.vetreach.coverage}%</p>
                  <p className="text-xs text-[#FF6B5E]">vs {result.comparison.baseline.coverage}%</p>
                  <p className="text-[10px] uppercase tracking-wide text-[#5B7E7A]">Coverage</p>
                </div>
                <div className="border border-[#CDE7E4] rounded bg-white p-3 text-center">
                  <p className="font-bold text-lg text-[#0F5C57]">{result.comparison.vetreach.animalsReached}</p>
                  <p className="text-xs text-[#FF6B5E]">vs {result.comparison.baseline.animalsReached}</p>
                  <p className="text-[10px] uppercase tracking-wide text-[#5B7E7A]">Animals</p>
                </div>
                
                <div className="border border-[#CDE7E4] rounded bg-white p-3 text-center">
                  <p className="font-bold text-lg text-[#0F5C57]">{result.comparison.vetreach.travelBurden}</p>
                  <p className="text-xs text-[#FF6B5E]">vs {result.comparison.baseline.travelBurden}</p>
                  <p className="text-[10px] uppercase tracking-wide text-[#5B7E7A]">Travel</p>
                </div>
              </div>
            </div>
          )}
        </section>
      </main>

      <footer className="max-w-3xl mx-auto px-6 pb-6 text-xs text-[#5B7E7A]">
        Farm data is simulated for demonstration, scaled to real Limpopo provincial livestock statistics.
      </footer>
    </div>
  )
}

export default App
