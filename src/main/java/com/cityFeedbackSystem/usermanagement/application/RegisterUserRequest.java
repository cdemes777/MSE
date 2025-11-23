package com.cityFeedbackSystem.usermanagement.application;

public class RegisterUserRequest {
    private final String userId;
    private final String email;
    private final String rawPassword;

    public RegisterUserRequest(String userId, String email, String rawPassword) {
        this.userId = userId;
        this.email = email;
        this.rawPassword = rawPassword;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getRawPassword() {
        return rawPassword;
    }
}