// EntitlementController.java

package com.armrt.controller;

@RestController
@RequestMapping("/api/entitlements")
public class EntitlementController {
   private final SavivyntIntegrationService savivyntService;

   public EntitlementController(SavivyntIntegrationService savivyntService) {
       this.savivyntService = savivyntService;
   }

   @GetMapping("/user/{userId}")
   public ResponseEntity<List<Entitlement>> getUserEntitlements(@PathVariable String userId) {
       List<Entitlement> entitlements = savivyntService.retrieveEntitlements(userId);
       return ResponseEntity.ok(entitlements);
   }
}