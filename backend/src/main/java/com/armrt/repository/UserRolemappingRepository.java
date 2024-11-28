// UserRoleMappingRepository.java

package com.armrt.repository;

public interface UserRoleMappingRepository extends MongoRepository<UserRoleMapping, String> {
    List<UserRoleMapping> findByUserId(String userId);
}