// EntitlementRepository.java 
public interface EntitlementRepository extends MongoRepository<Entitlement, String> {
    List<Entitlement> findByUserId(String userId);
}