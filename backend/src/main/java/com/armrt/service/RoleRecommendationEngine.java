package com.armrt.service;

import com.armrt.model.AccessActivityLog;
import com.armrt.model.Entitlement;
import com.armrt.model.RoleRecommendation;
import com.armrt.model.UserRoleMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * RoleRecommendationEngine handles the logic for analyzing user roles,
 * entitlements, and access activity logs to generate optimized role recommendations.
 */
public class RoleRecommendationEngine {
    private final List<UserRoleMapping> currentRoles;
    private final List<Entitlement> activeEntitlements;
    private final AccessActivityLog activityLog;

    public RoleRecommendationEngine(List<UserRoleMapping> currentRoles, List<Entitlement> activeEntitlements, AccessActivityLog activityLog) {
        this.currentRoles = currentRoles;
        this.activeEntitlements = activeEntitlements;
        this.activityLog = activityLog;
    }

    /**
     * Computes optimized roles based on the least-privilege principle.
     *
     * @return a list of RoleRecommendation objects
     */
    public List<RoleRecommendation> computeOptimizedRoles() {
        List<RoleRecommendation> recommendations = new ArrayList<>();

        // Placeholder logic for generating recommendations
        for (UserRoleMapping roleMapping : currentRoles) {
            RoleRecommendation recommendation = new RoleRecommendation();
            recommendation.setUserId(roleMapping.getUserId());
            recommendation.setCurrentRoles(roleMapping.getRoles());
            recommendation.setRecommendedRoles(generateRecommendedRoles(roleMapping));
            recommendation.setRiskScore(computeRiskScore(roleMapping));
            recommendation.setValidated(false); // Set to false initially, require admin approval
            recommendations.add(recommendation);
        }

        return recommendations;
    }

    /**
     * Generates recommended roles for a user based on their current roles.
     *
     * @param roleMapping the user's current role mapping
     * @return a list of recommended roles
     */
    private List<String> generateRecommendedRoles(UserRoleMapping roleMapping) {
        List<String> recommendedRoles = new ArrayList<>();

        // Sample logic: replace all roles starting with "admin" to "user"
        for (String role : roleMapping.getRoles()) {
            if (role.startsWith("admin")) {
                recommendedRoles.add(role.replace("admin", "user"));
            } else {
                recommendedRoles.add(role);
            }
        }

        return recommendedRoles;
    }

    /**
     * Computes a risk score for a user based on their access activity and entitlements.
     *
     * @param roleMapping the user's current role mapping
     * @return a calculated risk score
     */
    private double computeRiskScore(UserRoleMapping roleMapping) {
        double riskScore = 0.0;

        // Sample logic: increase risk score based on the number of roles
        riskScore += roleMapping.getRoles().size() * 5;

        // Additional logic can include analyzing entitlements and activity logs
        if (activeEntitlements.size() > 10) {
            riskScore += 10;
        }

        return riskScore;
    }
}
