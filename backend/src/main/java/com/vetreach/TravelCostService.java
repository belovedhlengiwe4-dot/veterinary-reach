package com.vetreach;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TravelCostService {

    private final MunicipalityRepository municipalityRepository;
    private final VeterinaryFacilityRepository facilityRepository;
    private final TravelCostRepository travelCostRepository;

    public TravelCostService(MunicipalityRepository municipalityRepository,
                              VeterinaryFacilityRepository facilityRepository,
                              TravelCostRepository travelCostRepository) {
        this.municipalityRepository = municipalityRepository;
        this.facilityRepository = facilityRepository;
        this.travelCostRepository = travelCostRepository;
    }

    public void generateAllTravelCosts() {
        List<Municipality> municipalities = municipalityRepository.findAll();
        List<VeterinaryFacility> facilities = facilityRepository.findAll();

        for (Municipality m : municipalities) {
            for (VeterinaryFacility f : facilities) {
                double distance = DistanceCalculator.calculateDistanceKm(
                        m.getLatitude(), m.getLongitude(),
                        f.getLatitude(), f.getLongitude());

                double time = DistanceCalculator.estimateTravelTimeMinutes(distance);

                TravelCost cost = new TravelCost();
                cost.setMunicipality(m);
                cost.setFacility(f);
                cost.setDistanceKm(distance);
                cost.setTravelTimeMin(time);
             
                travelCostRepository.save(cost);
            }
        }
    }
}