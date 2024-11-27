// Entitlement.java
@Document(collection = "entitlements")
public class Entitlement {
    @Id
    private String id;
    private String userId;
    private String name;
    private boolean isExpired;
}