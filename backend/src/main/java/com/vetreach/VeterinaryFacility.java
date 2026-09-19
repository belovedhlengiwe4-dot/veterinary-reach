package com.vetreach;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class VeterinaryFacility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facilityId;

    private String name;
    private String facilityType;
    private double latitude;
    private double longitude;
    private boolean productionAnimalRelevant;
    private boolean mobileService;

    @ManyToOne
    @JoinColumn(name = "municipality_id")
    private Municipality municipality;

    // Getters and setters
    public Long getFacilityId() { return facilityId; }
    public void setFacilityId(Long facilityId) { this.facilityId = facilityId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getFacilityType() { return facilityType; }
    public void setFacilityType(String facilityType) { this.facilityType = facilityType; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public boolean isProductionAnimalRelevant() { return productionAnimalRelevant; }
    public void setProductionAnimalRelevant(boolean productionAnimalRelevant) { this.productionAnimalRelevant = productionAnimalRelevant; }

    public boolean isMobileService() { return mobileService; }
    public void setMobileService(boolean mobileService) { this.mobileService = mobileService; }

    public Municipality getMunicipality() { return municipality; }
    public void setMunicipality(Municipality municipality) { this.municipality = municipality; }
}