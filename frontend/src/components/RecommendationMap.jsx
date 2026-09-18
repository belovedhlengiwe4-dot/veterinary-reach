import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet'
import 'leaflet/dist/leaflet.css'

function RecommendationMap({ recommendations }) {
  const limpopoCenter = [-23.4013, 29.4179]

  return (
    <div className="w-full h-[500px] rounded-xl overflow-hidden">
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
              <strong>{facility.name}</strong>
              <br />
              Travel time: {facility.averageTravelTime?.toFixed(1)} minutes
            </Popup>
          </Marker>
        ))}
      </MapContainer>
    </div>
  )
}

export default RecommendationMap