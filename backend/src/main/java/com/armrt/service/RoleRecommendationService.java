// RoleRecommendationService.java

package com.armrt.service;

@Service
public class RoleRecommendationService {
   private final SavivyntIntegrationService savivyntService;
   private final RoleRecommendationRepository repository;

   public RoleRecommendationService(SavivyntIntegrationService savivyntService, RoleRecommendationRepository repository) {
       this.savivyntService = savivyntService;
       this.repository = repository;
   }

   public RoleRecommendationEngine generateRecommendations(String userId) {
       List<UserRoleMapping> currentRoles = savivyntService.fetchUserRoleMappings(userId);
       List<Entitlement> activeEntitlements = savivyntService.retrieveEntitlements(userId);
       AccessActivityLog activityLog = savivyntService.fetchAccessLogs(userId, LocalDate.now().minusMonths(6));
       RoleRecommendationEngine engine = new RoleRecommendationEngine(currentRoles, activeEntitlements, activityLog);
       List<RoleRecommendation> recommendations = engine.computeOptimizedRoles();
       repository.saveAll(recommendations);
       return engine;
   }

   public void approveRecommendation(RoleRecommendation recommendation) {
       if (!recommendation.isValidated()) {
           throw new ValidationException("Recommendation requires additional review");
       }
       savivyntService.updateUserRoles(recommendation.getUserId(), recommendation.getRecommendedRoles());
       auditLogService.recordRoleChange(recommendation);
   }
}
