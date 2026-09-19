package com.vetreach;

public class ComparisonResult {
    private ComparisonMetrics vetreach;
    private ComparisonMetrics baseline;

    public ComparisonResult(ComparisonMetrics vetreach, ComparisonMetrics baseline) {
        this.vetreach = vetreach;
        this.baseline = baseline;
    }

    public ComparisonMetrics getVetreach() { return vetreach; }
    public ComparisonMetrics getBaseline() { return baseline; }
}