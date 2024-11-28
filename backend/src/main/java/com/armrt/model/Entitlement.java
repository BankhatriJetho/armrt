// Entitlement.java

package com.armrt.model;

@Document(collection = "entitlements")
public class Entitlement {
    @Id
    private String id;
    private String userId;
    private String name;
    private boolean isExpired;
}