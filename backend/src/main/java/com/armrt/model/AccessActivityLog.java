// AccessActivityLog.java

package com.armrt.model;

@Document(collection = "access_logs")
public class AccessActivityLog {
    @Id
    private String id;
    private String userId;
    private LocalDateTime accessTime;
    private String action;
    private String resource;
}