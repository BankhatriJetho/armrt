package com.armrt.repository;

import com.armrt.model.UserRoleMapping;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository interface for managing UserRoleMapping entities.
 */
public interface UserRoleMappingRepository extends MongoRepository<UserRoleMapping, String> {
    /**
     * Finds all user-role mappings for a specific user.
     *
     * @param userId the user ID
     * @return a list of user-role mappings for the given user
     */
    List<UserRoleMapping> findByUserId(String userId);
}
