package com.armrt.repository;

import com.armrt.model.RoleRecommendation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository interface for managing RoleRecommendation entities.
 */
public interface RoleRecommendationRepository extends MongoRepository<RoleRecommendation, String> {
    /**
     * Finds all role recommendations for a specific user.
     *
     * @param userId the user ID
     * @return a list of role recommendations for the given user
     */
    List<RoleRecommendation> findByUserId(String userId);
}
