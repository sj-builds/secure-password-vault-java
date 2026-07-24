package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Credential {

    private String website;
    private String username;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JsonCreator
    public Credential(
            @JsonProperty("website") String website,
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("createdAt") LocalDateTime createdAt,
            @JsonProperty("updatedAt") LocalDateTime updatedAt) {

        this.website = website;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Credential(String website, String username, String password) {
        this(
                website,
                username,
                password,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
        touch();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        touch();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        touch();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getMaskedPassword() {

        if (password == null || password.isEmpty()) {
            return "";
        }

        return "*".repeat(password.length());
    }

    private void touch() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {

        return """
                =========================================
                Website : %s
                Username: %s
                Password: %s
                Created : %s
                Updated : %s
                =========================================
                """.formatted(
                website,
                username,
                getMaskedPassword(),
                createdAt,
                updatedAt
        );
    }
}