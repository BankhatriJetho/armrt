// RoleRecommendationRepository.java

package com.armrt.repository;

public interface RoleRecommendationRepository extends MongoRepository<RoleRecommendation, String> {
    List<RoleRecommendation> findByUserId(String userId);
}