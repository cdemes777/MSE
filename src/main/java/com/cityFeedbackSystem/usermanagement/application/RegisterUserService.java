package com.cityFeedbackSystem.usermanagement.application;

import com.cityFeedbackSystem.usermanagement.domain.model.User;
import com.cityFeedbackSystem.usermanagement.domain.services.PasswordHashingService;
import com.cityFeedbackSystem.usermanagement.domain.valueobjects.EmailAddress;
import com.cityFeedbackSystem.usermanagement.domain.valueobjects.PasswordHash;
import com.cityFeedbackSystem.usermanagement.domain.valueobjects.UserRole;
import com.cityFeedbackSystem.usermanagement.domain.valueobjects.UserStatus;

public class RegisterUserService {

    private final PasswordHashingService hashingService;

    public RegisterUserService(PasswordHashingService hashingService) {
        this.hashingService = hashingService;
    }

    public User register(RegisterUserRequest request) {
        EmailAddress emailAddress = new EmailAddress(request.getEmail());
        PasswordHash passwordHash = hashingService.hash(request.getRawPassword());
        UserRole role = UserRole.CITIZEN;
        UserStatus status = UserStatus.ACTIVE;

        return new User(request.getUserId(), emailAddress, passwordHash, role, status);
    }
}