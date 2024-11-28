// UserRoleMapping.java

package com.armrt.model;


@Document(collection = "user_role_mappings")
public class UserRoleMapping {
    @Id
    private String id;
    private String userId;
    private List<String> roles;
    private boolean isActive;
}