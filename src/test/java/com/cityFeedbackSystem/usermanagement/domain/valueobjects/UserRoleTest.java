package com.cityFeedbackSystem.usermanagement.domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Tests for UserRole enum.
 */
class UserRoleTest {

    @Test
    void shouldHaveThreeRoles() {
        // Given & When
        UserRole[] roles = UserRole.values();

        // Then
        assertEquals(3, roles.length);
    }

    @Test
    void shouldHaveCitizenRole() {
        // Given & When
        UserRole role = UserRole.CITIZEN;

        // Then
        assertNotNull(role);
        assertEquals("CITIZEN", role.name());
    }

    @Test
    void shouldHaveStaffRole() {
        // Given & When
        UserRole role = UserRole.STAFF;

        // Then
        assertNotNull(role);
        assertEquals("STAFF", role.name());
    }

    @Test
    void shouldHaveAdminRole() {
        // Given & When
        UserRole role = UserRole.ADMIN;

        // Then
        assertNotNull(role);
        assertEquals("ADMIN", role.name());
    }

    @Test
    void shouldBeAbleToGetRoleByName() {
        // Given & When
        UserRole citizen = UserRole.valueOf("CITIZEN");
        UserRole staff = UserRole.valueOf("STAFF");
        UserRole admin = UserRole.valueOf("ADMIN");

        // Then
        assertEquals(UserRole.CITIZEN, citizen);
        assertEquals(UserRole.STAFF, staff);
        assertEquals(UserRole.ADMIN, admin);
    }
}

