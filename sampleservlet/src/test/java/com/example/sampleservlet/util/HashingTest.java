package com.example.sampleservlet.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashingTest {

    private final Hashing hashing = new Hashing();

    @Test
    void shouldHashDataSuccessfully() {
        String plainText = "mySecretPassword";

        String hashed = hashing.hashData(plainText);

        assertNotNull(hashed);
        assertNotEquals(plainText, hashed,"success");
        assertTrue(hashed.startsWith("$2"),"success"); // BCrypt hash format
    }

    @Test
    void shouldVerifyPasswordSuccessfully() {
        String plainText = "mySecretPassword";

        String hashed = hashing.hashData(plainText);

        boolean result = hashing.verifyPassword(plainText, hashed);

        assertTrue(result,"success");
    }

    @Test
    void shouldFailForWrongPassword() {
        String plainText = "mySecretPassword";
        String wrongPassword = "wrongPassword";

        String hashed = hashing.hashData(plainText);

        boolean result = hashing.verifyPassword(wrongPassword, hashed);

        assertFalse(result,"success");
    }
}
