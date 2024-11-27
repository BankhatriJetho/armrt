// SavivyntConfig.java
@Configuration
public class SavivyntConfig {
   @Value("${saviynt.api.endpoint}")
   private String apiEndpoint;

   @Value("${saviynt.client.id}")
   private String clientId;

   @Value("${saviynt.client.secret}")
   private String clientSecret;

   @Bean
   public OAuth2RestTemplate savivyntRestTemplate(OAuth2ClientContext oAuth2ClientContext) {
       AuthorizationCodeResourceDetails resourceDetails = new AuthorizationCodeResourceDetails();
       resourceDetails.setAccessTokenUri(apiEndpoint + "/oauth/token");
       resourceDetails.setClientId(clientId);
       resourceDetails.setClientSecret(clientSecret);
       resourceDetails.setScope(Arrays.asList("read:user-roles", "read:entitlements", "read:access-logs"));

       return new OAuth2RestTemplate(resourceDetails, oAuth2ClientContext);
   }
}