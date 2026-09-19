import requests
import psycopg2
import time

# PostgreSQL connection
DB_CONFIG = {
    "host": "localhost",
    "port": 5432,
    "database": "vetreach",
    "user": "postgres",
    "password": "Paula@1984"
}

# Public OSRM server
OSRM_URL = "https://router.project-osrm.org/route/v1/driving"

# Connect to PostgreSQL
connection = psycopg2.connect(**DB_CONFIG)
cursor = connection.cursor()

# Get all municipalities
cursor.execute("""
    SELECT municipality_id, latitude, longitude
    FROM municipality
    ORDER BY municipality_id
""")

municipalities = cursor.fetchall()

# Get all veterinary facilities
cursor.execute("""
    SELECT facility_id, latitude, longitude
    FROM veterinary_facility
    ORDER BY facility_id
""")

facilities = cursor.fetchall()

print(f"Found {len(facilities)} veterinary facilities.")
print(f"Found {len(municipalities)} municipalities.")

# Clear existing travel-cost records
cursor.execute("DELETE FROM travel_cost")
connection.commit()

count = 0

for facility_id, facility_lat, facility_lon in facilities:

    for municipality_id, municipality_lat, municipality_lon in municipalities:

        try:
            url = (
                f"{OSRM_URL}/"
                f"{facility_lon},{facility_lat};"
                f"{municipality_lon},{municipality_lat}"
                "?overview=false"
            )

            response = requests.get(url, timeout=30)
            response.raise_for_status()

            data = response.json()

            if data["code"] != "Ok":
                print(
                    f"OSRM failed: facility {facility_id} "
                    f"to municipality {municipality_id}"
                )
                continue

            route = data["routes"][0]

            distance_km = route["distance"] / 1000
            travel_time_min = route["duration"] / 60

            cursor.execute("""
                INSERT INTO travel_cost
                (
                    distance_km,
                    travel_time_min,
                    routing_method,
                    municipality_id,
                    facility_id
                )
                VALUES (%s, %s, %s, %s, %s)
            """, (
                distance_km,
                travel_time_min,
                "OSRM",
                municipality_id,
                facility_id
            ))

            connection.commit()

            count += 1

            print(
                f"{count}: Facility {facility_id} -> "
                f"Municipality {municipality_id} | "
                f"{distance_km:.2f} km | "
                f"{travel_time_min:.1f} min"
            )

            # Avoid sending requests too quickly
            time.sleep(0.2)

        except Exception as e:
            print(
                f"Error for facility {facility_id} -> "
                f"municipality {municipality_id}: {e}"
            )

cursor.close()
connection.close()

print()
print(f"Finished. Inserted {count} travel-cost records.")