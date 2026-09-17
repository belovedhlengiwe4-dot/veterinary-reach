package com.vetreach;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class TravelCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double distanceKm;
    private double travelTimeMin;
    private String routingMethod;

    @ManyToOne
    @JoinColumn(name = "municipality_id")
    private Municipality municipality;

    @ManyToOne
    @JoinColumn(name = "facility_id")
    private VeterinaryFacility facility;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getDistanceKm() { return distanceKm; }
    public void setDistanceKm(double distanceKm) { this.distanceKm = distanceKm; }

    public double getTravelTimeMin() { return travelTimeMin; }
    public void setTravelTimeMin(double travelTimeMin) { this.travelTimeMin = travelTimeMin; }

    public String getRoutingMethod() { return routingMethod; }
    public void setRoutingMethod(String routingMethod) { this.routingMethod = routingMethod; }

    public Municipality getMunicipality() { return municipality; }
    public void setMunicipality(Municipality municipality) { this.municipality = municipality; }

    public VeterinaryFacility getFacility() { return facility; }
    public void setFacility(VeterinaryFacility facility) { this.facility = facility; }
}
