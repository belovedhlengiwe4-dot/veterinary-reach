package com.vetreach;

import java.util.List;

public class RecommendationResponse {
    private List<RecommendationResult> recommendations;
    private ComparisonResult comparison;

    public RecommendationResponse(List<RecommendationResult> recommendations, ComparisonResult comparison) {
        this.recommendations = recommendations;
        this.comparison = comparison;
    }

    public List<RecommendationResult> getRecommendations() { return recommendations; }
    public ComparisonResult getComparison() { return comparison; }
}
