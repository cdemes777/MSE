package com.cityFeedbackSystem.usermanagement.domain.model;

import com.cityFeedbackSystem.usermanagement.domain.valueobjects.EmailAddress;
import com.cityFeedbackSystem.usermanagement.domain.valueobjects.PasswordHash;
import com.cityFeedbackSystem.usermanagement.domain.valueobjects.UserRole;
import com.cityFeedbackSystem.usermanagement.domain.valueobjects.UserStatus;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Aggregate Root representing a User in the system.
 * According to DDD, this is the main entity for the Usermanagement bounded context.
 */
public class User {

    private final String id;
    private final String name;
    private final EmailAddress email;
    private final PasswordHash passwordHash;
    private final UserRole role;
    private final UserStatus status;
    private LocalDateTime lastLogin;

    public User(String name, EmailAddress email, PasswordHash passwordHash, UserRole role, UserStatus status) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.status = status;
        this.lastLogin = null;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public EmailAddress getEmail() {
        return email;
    }

    public PasswordHash getPasswordHash() {
        return passwordHash;
    }

    public UserRole getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void updateLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }
}

