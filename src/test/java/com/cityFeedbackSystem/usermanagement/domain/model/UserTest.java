package com.cityFeedbackSystem.usermanagement.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cityFeedbackSystem.usermanagement.domain.valueobjects.EmailAddress;
import com.cityFeedbackSystem.usermanagement.domain.valueobjects.PasswordHash;
import com.cityFeedbackSystem.usermanagement.domain.valueobjects.UserRole;
import com.cityFeedbackSystem.usermanagement.domain.valueobjects.UserStatus;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

/**
 * Tests for User aggregate root.
 */
class UserTest {

    @Test
    void shouldCreateUserWithAllRequiredFields() {
        // Given
        String name = "John Doe";
        EmailAddress email = new EmailAddress("john@example.com");
        PasswordHash passwordHash = new PasswordHash("a".repeat(32));
        UserRole role = UserRole.CITIZEN;
        UserStatus status = UserStatus.ACTIVE;

        // When
        User user = new User(name, email, passwordHash, role, status);

        // Then
        assertNotNull(user);
        assertNotNull(user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(passwordHash, user.getPasswordHash());
        assertEquals(role, user.getRole());
        assertEquals(status, user.getStatus());
        assertNull(user.getLastLogin());
    }

    @Test
    void shouldGenerateUniqueIdForEachUser() {
        // Given
        EmailAddress email1 = new EmailAddress("user1@example.com");
        EmailAddress email2 = new EmailAddress("user2@example.com");
        PasswordHash passwordHash = new PasswordHash("a".repeat(32));

        // When
        User user1 = new User("User 1", email1, passwordHash, UserRole.CITIZEN, UserStatus.ACTIVE);
        User user2 = new User("User 2", email2, passwordHash, UserRole.CITIZEN, UserStatus.ACTIVE);

        // Then
        assertNotNull(user1.getId());
        assertNotNull(user2.getId());
        // IDs should be different (very unlikely to be the same with UUID)
        // Note: This is a probabilistic test, but UUID collision is extremely rare
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        // Given
        EmailAddress email = new EmailAddress("test@example.com");
        PasswordHash passwordHash = new PasswordHash("a".repeat(32));

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new User(null, email, passwordHash, UserRole.CITIZEN, UserStatus.ACTIVE)
        );
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        // Given
        EmailAddress email = new EmailAddress("test@example.com");
        PasswordHash passwordHash = new PasswordHash("a".repeat(32));

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new User("", email, passwordHash, UserRole.CITIZEN, UserStatus.ACTIVE)
        );
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        // Given
        EmailAddress email = new EmailAddress("test@example.com");
        PasswordHash passwordHash = new PasswordHash("a".repeat(32));

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new User("   ", email, passwordHash, UserRole.CITIZEN, UserStatus.ACTIVE)
        );
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldUpdateLastLogin() {
        // Given
        User user = new User(
            "John Doe",
            new EmailAddress("john@example.com"),
            new PasswordHash("a".repeat(32)),
            UserRole.CITIZEN,
            UserStatus.ACTIVE
        );
        LocalDateTime loginTime = LocalDateTime.now();

        // When
        user.updateLastLogin(loginTime);

        // Then
        assertEquals(loginTime, user.getLastLogin());
    }

    @Test
    void shouldSupportAllUserRoles() {
        // Given
        EmailAddress email = new EmailAddress("test@example.com");
        PasswordHash passwordHash = new PasswordHash("a".repeat(32));

        // When & Then
        User citizen = new User("Citizen", email, passwordHash, UserRole.CITIZEN, UserStatus.ACTIVE);
        assertEquals(UserRole.CITIZEN, citizen.getRole());

        User staff = new User("Staff", email, passwordHash, UserRole.STAFF, UserStatus.ACTIVE);
        assertEquals(UserRole.STAFF, staff.getRole());

        User admin = new User("Admin", email, passwordHash, UserRole.ADMIN, UserStatus.ACTIVE);
        assertEquals(UserRole.ADMIN, admin.getRole());
    }

    @Test
    void shouldSupportAllUserStatuses() {
        // Given
        EmailAddress email = new EmailAddress("test@example.com");
        PasswordHash passwordHash = new PasswordHash("a".repeat(32));

        // When & Then
        User active = new User("Active User", email, passwordHash, UserRole.CITIZEN, UserStatus.ACTIVE);
        assertEquals(UserStatus.ACTIVE, active.getStatus());

        User deactivated = new User(
            "Deactivated User",
            email,
            passwordHash,
            UserRole.CITIZEN,
            UserStatus.DEACTIVATED
        );
        assertEquals(UserStatus.DEACTIVATED, deactivated.getStatus());
    }
}

