import 'leaflet/dist/leaflet.css'
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet'
import MarkerClusterGroup from 'react-leaflet-cluster'

function RecommendationMap({ recommendations }) {
  const limpopoCenter = [-23.4013, 29.4179]

  return (
    <div className="w-full h-[300px] rounded-xl overflow-hidden">
      <MapContainer
        center={limpopoCenter}
        zoom={7}
        scrollWheelZoom={true}
        className="w-full h-full"
      >
        <TileLayer
          attribution="&copy; OpenStreetMap contributors"
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />

        {recommendations?.map((facility, index) => (
          <Marker
            key={index}
            position={[facility.latitude, facility.longitude]}
          >
            <Popup>
              <div className="space-y-1">
                <strong>{facility.location}</strong>

                <div>
                  <strong>Reason:</strong> {facility.reason}
                </div>

                <div>
                  <strong>Animals reached:</strong> {facility.animalsReached}
                </div>

                <div>
                  <strong>Travel time:</strong> {Math.round(facility.travelTime)} minutes
                </div>

                <div>
                  <strong>Disease risk:</strong> {facility.diseaseRisk}
                </div>
              </div>
            </Popup>
          </Marker>
        ))}
      </MapContainer>
    </div>
  )
}

export default RecommendationMap