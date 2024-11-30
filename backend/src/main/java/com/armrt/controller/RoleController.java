package com.armrt.controller;

import com.armrt.model.RoleRecommendation;
import com.armrt.model.UserRoleMapping;
import com.armrt.service.RoleRecommendationService;
import com.armrt.service.SaviyntIntegrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final SaviyntIntegrationService saviyntService;
    private final RoleRecommendationService roleService;

    public RoleController(SaviyntIntegrationService saviyntService, RoleRecommendationService roleService) {
        this.saviyntService = saviyntService;
        this.roleService = roleService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserRoleMapping>> getUserRoles(@PathVariable String userId) {
        List<UserRoleMapping> roles = saviyntService.fetchUserRoleMappings(userId);
        return ResponseEntity.ok(roles);
    }

    @PostMapping("/recommend/{userId}")
    public ResponseEntity<List<RoleRecommendation>> generateRoleRecommendations(@PathVariable String userId) {
        List<RoleRecommendation> recommendations = roleService.generateRecommendations(userId);
        return ResponseEntity.ok(recommendations);
    }

    @PostMapping("/approve")
    public ResponseEntity<Void> approveRoleRecommendation(@RequestBody RoleRecommendation recommendation) {
        roleService.approveRecommendation(recommendation);
        return ResponseEntity.noContent().build();
    }
}
