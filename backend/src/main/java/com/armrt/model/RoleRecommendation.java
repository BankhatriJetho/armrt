package com.armrt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "role_recommendations")
public class RoleRecommendation {
    @Id
    private String id;
    private String userId;
    private List<String> currentRoles;
    private List<String> recommendedRoles;
    private double riskScore;
    private boolean isValidated;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getCurrentRoles() {
        return currentRoles;
    }

    public void setCurrentRoles(List<String> currentRoles) {
        this.currentRoles = currentRoles;
    }

    public List<String> getRecommendedRoles() {
        return recommendedRoles;
    }

    public void setRecommendedRoles(List<String> recommendedRoles) {
        this.recommendedRoles = recommendedRoles;
    }

    public double getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(double riskScore) {
        this.riskScore = riskScore;
    }

    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated(boolean validated) {
        isValidated = validated;
    }
}
