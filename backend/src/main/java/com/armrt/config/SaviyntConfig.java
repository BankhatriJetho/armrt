package com.armrt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.context.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.AuthorizationCodeResourceDetails;

import java.util.Arrays;

@Configuration
public class SaviyntConfig {

    @Value("${saviynt.api.endpoint}")
    private String apiEndpoint;

    @Value("${saviynt.client.id}")
    private String clientId;

    @Value("${saviynt.client.secret}")
    private String clientSecret;

    @Bean
    public OAuth2RestTemplate saviyntRestTemplate(OAuth2ClientContext oAuth2ClientContext) {
        AuthorizationCodeResourceDetails resourceDetails = new AuthorizationCodeResourceDetails();
        resourceDetails.setAccessTokenUri(apiEndpoint + "/oauth/token");
        resourceDetails.setClientId(clientId);
        resourceDetails.setClientSecret(clientSecret);
        resourceDetails.setScope(Arrays.asList("read:user-roles", "read:entitlements", "read:access-logs"));

        return new OAuth2RestTemplate(resourceDetails, oAuth2ClientContext);
    }
}
