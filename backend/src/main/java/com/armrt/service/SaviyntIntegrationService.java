// SavivyntIntegrationService.java
@Service
public class SavivyntIntegrationService {
   private final OAuth2RestTemplate oauth2RestTemplate;
   private final String apiEndpoint;

   public SavivyntIntegrationService(OAuth2RestTemplate oauth2RestTemplate, @Value("${saviynt.api.endpoint}") String apiEndpoint) {
       this.oauth2RestTemplate = oauth2RestTemplate;
       this.apiEndpoint = apiEndpoint;
   }

   public List<UserRoleMapping> fetchUserRoleMappings(String userId) {
       String url = apiEndpoint + "/user-roles?userId=" + userId;
       ResponseEntity<List<UserRoleMapping>> response = oauth2RestTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<UserRoleMapping>>() {});
       return response.getBody().stream().filter(UserRoleMapping::isActive).collect(Collectors.toList());
   }

   public List<Entitlement> retrieveEntitlements(String userId) {
       String url = apiEndpoint + "/entitlements?userId=" + userId;
       ResponseEntity<List<Entitlement>> response = oauth2RestTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Entitlement>>() {});
       return response.getBody().stream().filter(e -> !e.isExpired()).collect(Collectors.toList());
   }

   public AccessActivityLog fetchAccessLogs(String userId, LocalDate startDate) {
       String url = apiEndpoint + "/access-logs";
       MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
       params.add("userId", userId);
       params.add("startDate", startDate.toString());
       ResponseEntity<AccessActivityLog> response = oauth2RestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(params), AccessActivityLog.class);
       return response.getBody();
   }
}