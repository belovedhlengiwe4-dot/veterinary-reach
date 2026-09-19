package com.vetreach;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class RecommendationController {

    private final TravelCostRepository travelCostRepository;
    private final LivestockIndicatorRepository livestockIndicatorRepository;
    private final HealthIndicatorRepository healthIndicatorRepository;

    public RecommendationController(
            TravelCostRepository travelCostRepository,
            LivestockIndicatorRepository livestockIndicatorRepository,
            HealthIndicatorRepository healthIndicatorRepository) {

        this.travelCostRepository = travelCostRepository;
        this.livestockIndicatorRepository = livestockIndicatorRepository;
        this.healthIndicatorRepository = healthIndicatorRepository;
    }

    @GetMapping("/recommendations")
    public RecommendationResponse getRecommendations(
            @RequestParam(defaultValue = "2") int mobileUnits,
            @RequestParam(defaultValue = "60") double travelLimit,
            @RequestParam(defaultValue = "balanced") String priority) {

        List<TravelCost> allCosts = travelCostRepository.findAll();

        List<LivestockIndicator> livestockIndicators =
                livestockIndicatorRepository.findAll();

        List<HealthIndicator> healthIndicators =
                healthIndicatorRepository.findAll();

        // Total livestock per municipality
        Map<Municipality, Double> animalsByMunicipality =
                livestockIndicators.stream()
                        .collect(Collectors.groupingBy(
                                LivestockIndicator::getMunicipality,
                                Collectors.summingDouble(
                                        LivestockIndicator::getValue
                                )
                        ));

        // Average disease-risk indicator per municipality
        Map<Municipality, Double> diseaseRiskByMunicipality =
                healthIndicators.stream()
                        .collect(Collectors.groupingBy(
                                HealthIndicator::getMunicipality,
                                Collectors.averagingDouble(
                                        HealthIndicator::getIndicatorValue
                                )
                        ));

        // Only municipalities reachable within the user's travel limit
        Map<VeterinaryFacility, List<TravelCost>> byFacility =
                allCosts.stream()
                        .filter(cost ->
                                cost.getTravelTimeMin() <= travelLimit)
                        .collect(Collectors.groupingBy(
                                TravelCost::getFacility
                        ));

        // Score every possible deployment facility
     // Score every possible deployment facility
        List<RecommendationCandidate> allCandidates =
                byFacility.entrySet()
                        .stream()
                        .map(entry -> {

                            VeterinaryFacility facility = entry.getKey();

                            List<TravelCost> reachable = entry.getValue();

                            double animalsReached =
                                    reachable.stream()
                                            .map(TravelCost::getMunicipality)
                                            .filter(Objects::nonNull)
                                            .mapToDouble(municipality ->
                                                    animalsByMunicipality
                                                            .getOrDefault(
                                                                    municipality,
                                                                    0.0
                                                            )
                                            )
                                            .sum();

                            double diseaseRisk =
                                    reachable.stream()
                                            .map(TravelCost::getMunicipality)
                                            .filter(Objects::nonNull)
                                            .mapToDouble(municipality ->
                                                    diseaseRiskByMunicipality
                                                            .getOrDefault(
                                                                    municipality,
                                                                    0.0
                                                            )
                                            )
                                            .average()
                                            .orElse(0);

                            double averageTravelTime =
                                    reachable.stream()
                                            .mapToDouble(
                                                    TravelCost::getTravelTimeMin
                                            )
                                            .average()
                                            .orElse(0);

                            double score;

                            switch (priority.toLowerCase()) {

                                case "livestock":
                                    score =
                                            animalsReached
                                                    - averageTravelTime;
                                    break;

                                case "disease":
                                    score =
                                            (diseaseRisk * 100)
                                                    - averageTravelTime;
                                    break;

                                case "accessibility":
                                    score =
                                            -averageTravelTime;
                                    break;

                                case "balanced":
                                default:
                                    score =
                                            animalsReached
                                                    + (diseaseRisk * 100)
                                                    - averageTravelTime;
                                    break;
                            }

                            return new RecommendationCandidate(
                                    facility,
                                    animalsReached,
                                    diseaseRisk,
                                    averageTravelTime,
                                    score
                            );
                        })
                        .collect(Collectors.toList());


        // ---------------------------------------------------------
        // Greedy marginal-coverage selection
        // ---------------------------------------------------------

        List<RecommendationCandidate> candidates =
                new ArrayList<>();

        Set<Municipality> alreadyCovered =
                new HashSet<>();

        for (int unit = 0;
             unit < mobileUnits && !allCandidates.isEmpty();
             unit++) {

            RecommendationCandidate bestCandidate = null;
            double bestMarginalScore = Double.NEGATIVE_INFINITY;

            for (RecommendationCandidate candidate : allCandidates) {

                // Don't select the same facility twice
                if (candidates.contains(candidate)) {
                    continue;
                }

                List<TravelCost> reachable =
                        byFacility.getOrDefault(
                                candidate.getFacility(),
                                Collections.emptyList()
                        );

                // Municipalities that this facility can reach
                Set<Municipality> facilityMunicipalities =
                        reachable.stream()
                                .map(TravelCost::getMunicipality)
                                .filter(Objects::nonNull)
                                .collect(Collectors.toSet());

                // Only count municipalities that are NOT already covered
                Set<Municipality> newMunicipalities =
                        new HashSet<>(facilityMunicipalities);

                newMunicipalities.removeAll(alreadyCovered);

                // Calculate NEW animals reached
                double newAnimalsReached =
                        newMunicipalities.stream()
                                .mapToDouble(municipality ->
                                        animalsByMunicipality
                                                .getOrDefault(
                                                        municipality,
                                                        0.0
                                                )
                                )
                                .sum();

                /*
                 * Marginal score:
                 *
                 * New livestock coverage is rewarded.
                 * Disease risk is still considered.
                 * Travel time is still penalised.
                 *
                 * The important difference is that livestock is calculated
                 * only from municipalities not already covered.
                 */
                double marginalScore;

                switch (priority.toLowerCase()) {

                    case "livestock":
                        marginalScore =
                                newAnimalsReached
                                        - candidate.getAverageTravelTime();
                        break;

                    case "disease":
                        marginalScore =
                                (candidate.getDiseaseRisk() * 100)
                                        - candidate.getAverageTravelTime();
                        break;

                    case "accessibility":
                        marginalScore =
                                -candidate.getAverageTravelTime();
                        break;

                    case "balanced":
                    default:
                        marginalScore =
                                newAnimalsReached
                                        + (candidate.getDiseaseRisk() * 100)
                                        - candidate.getAverageTravelTime();
                        break;
                }

                if (marginalScore > bestMarginalScore) {

                    bestMarginalScore = marginalScore;

                    bestCandidate = candidate;
                }
            }

            if (bestCandidate == null) {
                break;
            }

            // Select this facility
            candidates.add(bestCandidate);

            // Add its newly covered municipalities
            List<TravelCost> selectedReachable =
                    byFacility.getOrDefault(
                            bestCandidate.getFacility(),
                            Collections.emptyList()
                    );

            selectedReachable.stream()
                    .map(TravelCost::getMunicipality)
                    .filter(Objects::nonNull)
                    .forEach(alreadyCovered::add);
        }
                                           

        // Convert candidates into API response objects
        List<RecommendationResult> recommendations =
                candidates.stream()
                        .map(candidate -> {

                            VeterinaryFacility facility =
                                    candidate.getFacility();

                            return new RecommendationResult(
                                    facility.getName(),
                                    facility.getLatitude(),
                                    facility.getLongitude(),
                                    "Selected using livestock demand, "
                                            + "disease risk and OSRM travel time",
                                    candidate.getAnimalsReached(),
                                    candidate.getAverageTravelTime(),
                                    getDiseaseRiskLevel(
                                            candidate.getDiseaseRisk()
                                    )
                            );
                        })
                        .collect(Collectors.toList());

        // Total livestock in the province
        double totalAnimals =
                livestockIndicators.stream()
                        .mapToDouble(LivestockIndicator::getValue)
                        .sum();

        // Livestock reached by recommended facilities
        Set<Municipality> coveredMunicipalities = new HashSet<>();

        for (RecommendationCandidate candidate : candidates) {

            List<TravelCost> reachable =
                    byFacility.getOrDefault(
                            candidate.getFacility(),
                            Collections.emptyList()
                    );

            reachable.stream()
                    .map(TravelCost::getMunicipality)
                    .filter(Objects::nonNull)
                    .forEach(coveredMunicipalities::add);
        }

        double totalAnimalsReached =
                coveredMunicipalities.stream()
                        .mapToDouble(municipality ->
                                animalsByMunicipality.getOrDefault(
                                        municipality,
                                        0.0
                                )
                        )
                        .sum();
        double coverage =
                totalAnimals > 0
                        ? Math.round(
                                (totalAnimalsReached / totalAnimals)
                                        * 10000.0
                          ) / 100.0
                        : 0;

        double averageTravelBurden =
                candidates.stream()
                        .mapToDouble(
                                RecommendationCandidate::getAverageTravelTime
                        )
                        .average()
                        .orElse(0);

        ComparisonMetrics vetreach =
                new ComparisonMetrics(
                        coverage,
                        totalAnimalsReached,
                        averageTravelBurden
                );
        /*
         * Baseline:
         * choose facilities with the lowest average OSRM travel time.
         */
        List<RecommendationCandidate> baselineCandidates =
                byFacility.entrySet()
                        .stream()
                        .map(entry -> {

                            VeterinaryFacility facility =
                                    entry.getKey();

                            List<TravelCost> reachable =
                                    entry.getValue();

                            double animalsReached =
                                    reachable.stream()
                                            .map(TravelCost::getMunicipality)
                                            .filter(Objects::nonNull)
                                            .mapToDouble(municipality ->
                                                    animalsByMunicipality
                                                            .getOrDefault(
                                                                    municipality,
                                                                    0.0
                                                            )
                                            )
                                            .sum();

                            double averageTravelTime =
                                    reachable.stream()
                                            .mapToDouble(
                                                    TravelCost::getTravelTimeMin
                                            )
                                            .average()
                                            .orElse(0);

                            return new RecommendationCandidate(
                                    facility,
                                    animalsReached,
                                    0,
                                    averageTravelTime,
                                    averageTravelTime
                            );
                        })
                        .sorted(
                                Comparator.comparingDouble(
                                        RecommendationCandidate
                                                ::getAverageTravelTime
                                )
                        )
                        .limit(mobileUnits)
                        .collect(Collectors.toList());

        double baselineAnimalsReached =
                baselineCandidates.stream()
                        .mapToDouble(
                                RecommendationCandidate::getAnimalsReached
                        )
                        .sum();

        double baselineCoverage =
                totalAnimals > 0
                        ? Math.round(
                                (baselineAnimalsReached / totalAnimals)
                                        * 10000.0
                          ) / 100.0
                        : 0;

        double baselineTravelBurden =
                baselineCandidates.stream()
                        .mapToDouble(
                                RecommendationCandidate::getAverageTravelTime
                        )
                        .average()
                        .orElse(0);

        ComparisonMetrics baseline =
                new ComparisonMetrics(
                        baselineCoverage,
                        baselineAnimalsReached,
                        baselineTravelBurden
                );
        ComparisonResult comparison =
                new ComparisonResult(
                        vetreach,
                        baseline
                );

        return new RecommendationResponse(
                recommendations,
                comparison
        );
    }

    private String getDiseaseRiskLevel(double riskValue) {

        /*
         * Health indicators are currently on a 1–4 scale.
         */
        if (riskValue > 2.5) {
            return "high";
        }

        if (riskValue > 1.5) {
            return "moderate";
        }

        if (riskValue > 0) {
            return "low";
        }

        return "none";
    }

    private static class RecommendationCandidate {

        private final VeterinaryFacility facility;
        private final double animalsReached;
        private final double diseaseRisk;
        private final double averageTravelTime;
        private final double score;

        public RecommendationCandidate(
                VeterinaryFacility facility,
                double animalsReached,
                double diseaseRisk,
                double averageTravelTime,
                double score) {

            this.facility = facility;
            this.animalsReached = animalsReached;
            this.diseaseRisk = diseaseRisk;
            this.averageTravelTime = averageTravelTime;
            this.score = score;
        }

        public VeterinaryFacility getFacility() {
            return facility;
        }

        public double getAnimalsReached() {
            return animalsReached;
        }

        public double getDiseaseRisk() {
            return diseaseRisk;
        }

        public double getAverageTravelTime() {
            return averageTravelTime;
        }

        public double getScore() {
            return score;
        }
    }
}