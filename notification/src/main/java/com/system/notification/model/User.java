package com.system.notification.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "users")
public class User {
    @Id
    private String Id;
    private String name;
    private String email;
    private String password;
    private Map<String, Boolean> notificationPreferences;
    private Long updatedOn;
    private Long createdOn;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = this.name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Boolean> getNotificationPreferences() {
        return notificationPreferences;
    }

    public void setNotificationPreferences(Map<String, Boolean> notificationPreferences) {
        this.notificationPreferences = notificationPreferences;
    }

    public Long getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Long updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id='" + Id + '\'' +
                ", username='" + name + '\'' +
                ", email='" + email + '\'' +
                ", notificationPreferences=" + notificationPreferences +
                ", updatedOn=" + updatedOn +
                ", createdOn=" + createdOn +
                '}';
    }
}
