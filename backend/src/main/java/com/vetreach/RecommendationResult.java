package com.vetreach;

public class RecommendationResult {
    private String location;
    private String reason;
    private double animalsReached;
    private int farmersReached;
    private double travelTimeMin;

    public RecommendationResult(String location, String reason, double animalsReached, int farmersReached, double travelTimeMin) {
        this.location = location;
        this.reason = reason;
        this.animalsReached = animalsReached;
        this.farmersReached = farmersReached;
        this.travelTimeMin = travelTimeMin;
    }

    public String getLocation() { return location; }
    public String getReason() { return reason; }
    public double getAnimalsReached() { return animalsReached; }
    public int getFarmersReached() { return farmersReached; }
    public double getTravelTimeMin() { return travelTimeMin; }
}
