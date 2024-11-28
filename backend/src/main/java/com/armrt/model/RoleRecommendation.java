// RoleRecommendation.java

package com.armrt.model;

@Document(collection = "role_recommendations")
public class RoleRecommendation {
    @Id
    private String id;
    private String userId;
    private List<String> currentRoles;
    private List<String> recommendedRoles;
    private double riskScore;
    private boolean isValidated;
}