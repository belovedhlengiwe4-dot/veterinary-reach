package com.vetreach;

public class RecommendationResult {

    private String location;
    private double latitude;
    private double longitude;
    private String reason;
    private double animalsReached;
    private double travelTime;
    private String diseaseRisk;

    public RecommendationResult(
            String location,
            double latitude,
            double longitude,
            String reason,
            double animalsReached,
            double travelTime,
            String diseaseRisk) {

        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.reason = reason;
        this.animalsReached = animalsReached;
        this.travelTime = travelTime;
        this.diseaseRisk = diseaseRisk;
    }

    public String getLocation() {
        return location;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getReason() {
        return reason;
    }

    public double getAnimalsReached() {
        return animalsReached;
    }

    public double getTravelTime() {
        return travelTime;
    }

    public String getDiseaseRisk() {
        return diseaseRisk;
    }
}
