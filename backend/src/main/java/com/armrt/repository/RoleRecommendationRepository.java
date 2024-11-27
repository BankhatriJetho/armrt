// RoleRecommendationRepository.java
public interface RoleRecommendationRepository extends MongoRepository<RoleRecommendation, String> {
    List<RoleRecommendation> findByUserId(String userId);
}