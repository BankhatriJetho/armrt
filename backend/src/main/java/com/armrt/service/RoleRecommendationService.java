package com.armrt.service;

import com.armrt.model.*;
import com.armrt.repository.RoleRecommendationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoleRecommendationService {
    private final SaviyntIntegrationService saviyntService;
    private final RoleRecommendationRepository repository;

    public RoleRecommendationService(SaviyntIntegrationService saviyntService, RoleRecommendationRepository repository) {
        this.saviyntService = saviyntService;
        this.repository = repository;
    }

    public List<RoleRecommendation> generateRecommendations(String userId) {
        List<UserRoleMapping> currentRoles = saviyntService.fetchUserRoleMappings(userId);
        List<Entitlement> activeEntitlements = saviyntService.retrieveEntitlements(userId);
        AccessActivityLog activityLog = saviyntService.fetchAccessLogs(userId, LocalDate.now().minusMonths(6));

        // Use an engine to compute recommendations and convert them to RoleRecommendation objects
        RoleRecommendationEngine engine = new RoleRecommendationEngine(currentRoles, activeEntitlements, activityLog);
        List<RoleRecommendation> recommendations = engine.computeOptimizedRoles();

        // Save recommendations to the repository
        repository.saveAll(recommendations);
        return recommendations;
    }

    public void approveRecommendation(RoleRecommendation recommendation) {
        if (!recommendation.isValidated()) {
            throw new ValidationException("Recommendation requires additional review");
        }
        saviyntService.updateUserRoles(recommendation.getUserId(), recommendation.getRecommendedRoles());
    }
}
