// UserRoleMappingRepository.java
public interface UserRoleMappingRepository extends MongoRepository<UserRoleMapping, String> {
    List<UserRoleMapping> findByUserId(String userId);
}