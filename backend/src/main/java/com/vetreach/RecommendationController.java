package com.vetreach;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class RecommendationController {

    private final VeterinaryFacilityRepository facilityRepository;
    private final TravelCostRepository travelCostRepository;

    public RecommendationController(VeterinaryFacilityRepository facilityRepository,
                                     TravelCostRepository travelCostRepository) {
        this.facilityRepository = facilityRepository;
        this.travelCostRepository = travelCostRepository;
    }

    @GetMapping("/recommendations")
    public RecommendationResponse getRecommendations(
            @RequestParam(defaultValue = "2") int mobileUnits,
            @RequestParam(defaultValue = "60") double travelLimit,
            @RequestParam(defaultValue = "Balanced") String priority) {

        List<TravelCost> allCosts = travelCostRepository.findAll();

        // Group travel costs by facility, keeping only ones within the travel limit
        Map<VeterinaryFacility, List<TravelCost>> byFacility = allCosts.stream()
                .filter(tc -> tc.getTravelTimeMin() <= travelLimit)
                .collect(Collectors.groupingBy(TravelCost::getFacility));

        // Placeholder scoring: rank facilities by how many municipalities they can reach
        List<RecommendationResult> recommendations = byFacility.entrySet().stream()
                .sorted((a, b) -> b.getValue().size() - a.getValue().size())
                .limit(mobileUnits)
                .map(entry -> {
                    VeterinaryFacility facility = entry.getKey();
                    List<TravelCost> reachable = entry.getValue();
                    double avgTravelTime = reachable.stream().mapToDouble(TravelCost::getTravelTimeMin).average().orElse(0);
                    double placeholderAnimals = reachable.size() * 250; // placeholder until real livestock totals are linked
                    int placeholderFarmers = reachable.size() * 12;      // placeholder until real farmer data exists

                    return new RecommendationResult(
                            facility.getName(),
                            "High demand + good access",
                            placeholderAnimals,
                            placeholderFarmers,
                            avgTravelTime
                    );
                })
                .collect(Collectors.toList());

        double totalAnimals = recommendations.stream().mapToDouble(RecommendationResult::getAnimalsReached).sum();
        int totalFarmers = recommendations.stream().mapToInt(RecommendationResult::getFarmersReached).sum();
        double totalTravelKm = allCosts.stream().mapToDouble(TravelCost::getDistanceKm).average().orElse(0);

        // Placeholder baseline (e.g. "send vet to biggest municipalities only") — replace with real logic later
        ComparisonResult comparison = new ComparisonResult(
                63.0, 48.0,
                totalAnimals, totalAnimals * 0.73,
                totalFarmers, (int)(totalFarmers * 0.75),
                totalTravelKm, totalTravelKm * 1.45
        );

        return new RecommendationResponse(recommendations, comparison);
    }
}