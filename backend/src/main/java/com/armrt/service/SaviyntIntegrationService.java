package com.armrt.service;

import com.armrt.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaviyntIntegrationService {
    private final WebClient webClient;

    @Value("${saviynt.api.endpoint}")
    private String apiEndpoint;

    public SaviyntIntegrationService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<UserRoleMapping> fetchUserRoleMappings(String userId) {
        String url = apiEndpoint + "/user-roles?userId=" + userId;
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(UserRoleMapping.class)
                .filter(UserRoleMapping::isActive)
                .collectList()
                .block();
    }

    public List<Entitlement> retrieveEntitlements(String userId) {
        String url = apiEndpoint + "/entitlements?userId=" + userId;
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(Entitlement.class)
                .filter(e -> !e.isExpired())
                .collectList()
                .block();
    }

    public AccessActivityLog fetchAccessLogs(String userId, LocalDate startDate) {
        String url = apiEndpoint + "/access-logs";
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("userId", userId)
                        .queryParam("startDate", startDate.toString())
                        .build())
                .retrieve()
                .bodyToMono(AccessActivityLog.class)
                .block();
    }
}
