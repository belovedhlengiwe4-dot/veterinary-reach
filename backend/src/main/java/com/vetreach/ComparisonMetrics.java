package com.vetreach;

public class ComparisonMetrics {

    private double coverage;
    private double animalsReached;
    private double travelBurden;

    public ComparisonMetrics(
            double coverage,
            double animalsReached,
            double travelBurden) {

        this.coverage = coverage;
        this.animalsReached = animalsReached;
        this.travelBurden = travelBurden;
    }

    public double getCoverage() {
        return coverage;
    }

    public double getAnimalsReached() {
        return animalsReached;
    }

    public double getTravelBurden() {
        return travelBurden;
    }
}