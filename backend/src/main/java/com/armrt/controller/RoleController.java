// RoleController.java
@RestController
@RequestMapping("/api/roles")
public class RoleController {
   private final SavivyntIntegrationService savivyntService;
   private final RoleRecommendationService roleService;

   public RoleController(SavivyntIntegrationService savivyntService, RoleRecommendationService roleService) {
       this.savivyntService = savivyntService;
       this.roleService = roleService;
   }

   @GetMapping("/user/{userId}")
   public ResponseEntity<List<UserRoleMapping>> getUserRoles(@PathVariable String userId) {
       List<UserRoleMapping> roles = savivyntService.fetchUserRoleMappings(userId);
       return ResponseEntity.ok(roles);
   }

   @PostMapping("/recommend/{userId}")
   public ResponseEntity<RoleRecommendationEngine> generateRoleRecommendations(@PathVariable String userId) {
       RoleRecommendationEngine recommendations = roleService.generateRecommendations(userId);
       return ResponseEntity.ok(recommendations);
   }

   @PostMapping("/approve")
   public ResponseEntity<Void> approveRoleRecommendation(@RequestBody RoleRecommendation recommendation) {
       roleService.approveRecommendation(recommendation);
       return ResponseEntity.noContent().build();
   }
}