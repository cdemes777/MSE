package com.cityFeedbackSystem.usermanagement.domain.valueobjects;

import java.util.Objects;

/**
 * Value Object representing a hashed password.
 * Ensures password hash validation and immutability.
 */
public final class PasswordHash {
    private final String value;

    public PasswordHash(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Password hash cannot be null or empty");
        }
        if (value.length() < 32) {
            throw new IllegalArgumentException("Password hash appears to be invalid (too short)");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PasswordHash that = (PasswordHash) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "PasswordHash{value='***'}";
    }
}

