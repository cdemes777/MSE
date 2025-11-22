package com.cityFeedbackSystem.usermanagement.domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Tests for UserStatus enum.
 */
class UserStatusTest {

    @Test
    void shouldHaveTwoStatuses() {
        // Given & When
        UserStatus[] statuses = UserStatus.values();

        // Then
        assertEquals(2, statuses.length);
    }

    @Test
    void shouldHaveActiveStatus() {
        // Given & When
        UserStatus status = UserStatus.ACTIVE;

        // Then
        assertNotNull(status);
        assertEquals("ACTIVE", status.name());
    }

    @Test
    void shouldHaveDeactivatedStatus() {
        // Given & When
        UserStatus status = UserStatus.DEACTIVATED;

        // Then
        assertNotNull(status);
        assertEquals("DEACTIVATED", status.name());
    }

    @Test
    void shouldBeAbleToGetStatusByName() {
        // Given & When
        UserStatus active = UserStatus.valueOf("ACTIVE");
        UserStatus deactivated = UserStatus.valueOf("DEACTIVATED");

        // Then
        assertEquals(UserStatus.ACTIVE, active);
        assertEquals(UserStatus.DEACTIVATED, deactivated);
    }
}

