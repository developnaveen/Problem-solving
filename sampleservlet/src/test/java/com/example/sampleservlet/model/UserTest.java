package com.example.sampleservlet.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldSetAndGetAllFields() {
        User user = new User();

        user.setEmail("user@test.com");
        user.setEmpId("EMP1001");
        user.setPassword("hashedPassword123");

        assertEquals("user@test.com", user.getEmail());
        assertEquals("EMP1001", user.getEmpId());
        assertEquals("hashedPassword123", user.getPassword());
    }
}
