package com.cityFeedbackSystem.usermanagement.domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests for EmailAddress value object.
 */
class EmailAddressTest {

    @Test
    void shouldCreateValidEmailAddress() {
        // Given
        String email = "test@example.com";

        // When
        EmailAddress emailAddress = new EmailAddress(email);

        // Then
        assertNotNull(emailAddress);
        assertEquals("test@example.com", emailAddress.getValue());
    }

    @Test
    void shouldNormalizeEmailToLowerCase() {
        // Given
        String email = "Test@Example.COM";

        // When
        EmailAddress emailAddress = new EmailAddress(email);

        // Then
        assertEquals("test@example.com", emailAddress.getValue());
    }

    @Test
    void shouldTrimWhitespace() {
        // Given
        String email = "  test@example.com  ";

        // When
        EmailAddress emailAddress = new EmailAddress(email);

        // Then
        assertEquals("test@example.com", emailAddress.getValue());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        // Given
        String email = null;

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new EmailAddress(email)
        );
        assertEquals("Email address cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsEmpty() {
        // Given
        String email = "";

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new EmailAddress(email)
        );
        assertEquals("Email address cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsBlank() {
        // Given
        String email = "   ";

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new EmailAddress(email)
        );
        assertEquals("Email address cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() {
        // Given
        String email = "invalid-email";

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new EmailAddress(email)
        );
        assertEquals("Invalid email address format: " + email, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailMissingAtSymbol() {
        // Given
        String email = "testexample.com";

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new EmailAddress(email));
    }

    @Test
    void shouldThrowExceptionWhenEmailMissingDomain() {
        // Given
        String email = "test@";

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new EmailAddress(email));
    }

    @Test
    void shouldBeEqualWhenEmailsAreSame() {
        // Given
        EmailAddress email1 = new EmailAddress("test@example.com");
        EmailAddress email2 = new EmailAddress("test@example.com");

        // When & Then
        assertEquals(email1, email2);
        assertEquals(email1.hashCode(), email2.hashCode());
    }

    @Test
    void shouldBeEqualWhenEmailsDifferOnlyInCase() {
        // Given
        EmailAddress email1 = new EmailAddress("Test@Example.COM");
        EmailAddress email2 = new EmailAddress("test@example.com");

        // When & Then
        assertEquals(email1, email2);
        assertEquals(email1.hashCode(), email2.hashCode());
    }

    @Test
    void shouldReturnEmailInToString() {
        // Given
        EmailAddress emailAddress = new EmailAddress("test@example.com");

        // When
        String result = emailAddress.toString();

        // Then
        assertEquals("test@example.com", result);
    }
}

