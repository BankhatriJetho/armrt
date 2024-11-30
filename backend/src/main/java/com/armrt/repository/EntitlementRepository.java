package com.armrt.repository;

import com.armrt.model.Entitlement;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository interface for managing Entitlement entities.
 */
public interface EntitlementRepository extends MongoRepository<Entitlement, String> {
    /**
     * Finds all entitlements associated with a specific user.
     *
     * @param userId the user ID
     * @return a list of entitlements for the given user
     */
    List<Entitlement> findByUserId(String userId);
}
