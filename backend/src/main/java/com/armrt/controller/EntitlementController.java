package com.armrt.controller;

import com.armrt.model.Entitlement;
import com.armrt.service.SaviyntIntegrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entitlements")
public class EntitlementController {
    private final SaviyntIntegrationService saviyntService;

    public EntitlementController(SaviyntIntegrationService saviyntService) {
        this.saviyntService = saviyntService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Entitlement>> getUserEntitlements(@PathVariable String userId) {
        List<Entitlement> entitlements = saviyntService.retrieveEntitlements(userId);
        return ResponseEntity.ok(entitlements);
    }
}
