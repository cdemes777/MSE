package com.cityFeedbackSystem.usermanagement.application;

import com.cityFeedbackSystem.usermanagement.domain.model.User;

public class UserController {

    private final RegisterUserService registerUserService;

    public UserController(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }

    public User registerUser(RegisterUserRequest request) {
        return registerUserService.register(request);
    }
}