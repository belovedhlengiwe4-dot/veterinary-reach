package com.vetreach;

public class ComparisonResult {
    private double coveragePercent;
    private double baselineCoveragePercent;
    private double totalAnimals;
    private double baselineAnimals;
    private int totalFarmers;
    private int baselineFarmers;
    private double totalTravelKm;
    private double baselineTravelKm;

    public ComparisonResult(double coveragePercent, double baselineCoveragePercent,
                             double totalAnimals, double baselineAnimals,
                             int totalFarmers, int baselineFarmers,
                             double totalTravelKm, double baselineTravelKm) {
        this.coveragePercent = coveragePercent;
        this.baselineCoveragePercent = baselineCoveragePercent;
        this.totalAnimals = totalAnimals;
        this.baselineAnimals = baselineAnimals;
        this.totalFarmers = totalFarmers;
        this.baselineFarmers = baselineFarmers;
        this.totalTravelKm = totalTravelKm;
        this.baselineTravelKm = baselineTravelKm;
    }

    public double getCoveragePercent() { return coveragePercent; }
    public double getBaselineCoveragePercent() { return baselineCoveragePercent; }
    public double getTotalAnimals() { return totalAnimals; }
    public double getBaselineAnimals() { return baselineAnimals; }
    public int getTotalFarmers() { return totalFarmers; }
    public int getBaselineFarmers() { return baselineFarmers; }
    public double getTotalTravelKm() { return totalTravelKm; }
    public double getBaselineTravelKm() { return baselineTravelKm; }
}