package com.cityFeedbackSystem.usermanagement.domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests for PasswordHash value object.
 */
class PasswordHashTest {

    @Test
    void shouldCreateValidPasswordHash() {
        // Given
        String hash = "a".repeat(32); // Minimum length hash

        // When
        PasswordHash passwordHash = new PasswordHash(hash);

        // Then
        assertNotNull(passwordHash);
        assertEquals(hash, passwordHash.getValue());
    }

    @Test
    void shouldThrowExceptionWhenHashIsNull() {
        // Given
        String hash = null;

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new PasswordHash(hash)
        );
        assertEquals("Password hash cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenHashIsEmpty() {
        // Given
        String hash = "";

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new PasswordHash(hash)
        );
        assertEquals("Password hash cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenHashIsBlank() {
        // Given
        String hash = "   ";

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new PasswordHash(hash)
        );
        assertEquals("Password hash cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenHashIsTooShort() {
        // Given
        String hash = "a".repeat(31); // Less than minimum length

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new PasswordHash(hash)
        );
        assertEquals("Password hash appears to be invalid (too short)", exception.getMessage());
    }

    @Test
    void shouldAcceptValidLengthHash() {
        // Given
        String hash = "a".repeat(64); // Typical hash length

        // When
        PasswordHash passwordHash = new PasswordHash(hash);

        // Then
        assertNotNull(passwordHash);
        assertEquals(hash, passwordHash.getValue());
    }

    @Test
    void shouldBeEqualWhenHashesAreSame() {
        // Given
        String hash = "a".repeat(32);
        PasswordHash hash1 = new PasswordHash(hash);
        PasswordHash hash2 = new PasswordHash(hash);

        // When & Then
        assertEquals(hash1, hash2);
        assertEquals(hash1.hashCode(), hash2.hashCode());
    }

    @Test
    void shouldMaskHashInToString() {
        // Given
        PasswordHash passwordHash = new PasswordHash("a".repeat(32));

        // When
        String result = passwordHash.toString();

        // Then
        assertEquals("PasswordHash{value='***'}", result);
    }
}

