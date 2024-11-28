// EntitlementRepository.java 

package com.armrt.repository;

public interface EntitlementRepository extends MongoRepository<Entitlement, String> {
    List<Entitlement> findByUserId(String userId);
}