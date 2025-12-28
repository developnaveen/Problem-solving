package com.example.csvwrite.service;

import com.example.csvwrite.dto.RegisterRequest;
import com.example.csvwrite.repository.UserRepository;
import com.example.csvwrite.springsecurityconfig.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private RegisterRequest request;

    @BeforeEach
    void setUp() {
        request = new RegisterRequest();
        request.setName("naveen");
        request.setPassword("password123");
    }

    // ---------------- REGISTER USER ----------------

    @Test
    void registerUser_success() {
        when(userRepository.isUserExists("naveen")).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("hashedPassword");
        when(userRepository.saveUser(any(RegisterRequest.class))).thenReturn(1);

        Boolean result = userService.registerUser(request);

        assertTrue(result);
        verify(userRepository).saveUser(any(RegisterRequest.class));
    }

    @Test
    void registerUser_userAlreadyExists() {
        when(userRepository.isUserExists("naveen")).thenReturn(true);

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> userService.registerUser(request)
        );

        assertEquals("User already exists", ex.getMessage());
        verify(userRepository, never()).saveUser(any());
    }

    // ---------------- LOGIN ----------------

    @Test
    void login_success() {
        when(userRepository.findPasswordByUser("naveen"))
                .thenReturn("hashedPassword");
        when(passwordEncoder.matches("password123", "hashedPassword"))
                .thenReturn(true);

        try (MockedStatic<JwtUtil> mockedJwt = mockStatic(JwtUtil.class)) {

            mockedJwt.when(() -> JwtUtil.generateToken("naveen"))
                    .thenReturn("mocked-jwt-token");

            String token = userService.login(request);

            assertEquals("mocked-jwt-token", token);
        }
    }

    @Test
    void login_userNotFound() {
        when(userRepository.findPasswordByUser("naveen"))
                .thenReturn(null);

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> userService.login(request)
        );

        assertEquals("User not found", ex.getMessage());
    }

    @Test
    void login_invalidPassword() {
        when(userRepository.findPasswordByUser("naveen"))
                .thenReturn("hashedPassword");
        when(passwordEncoder.matches("password123", "hashedPassword"))
                .thenReturn(false);

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> userService.login(request)
        );

        assertEquals("Invalid credentials", ex.getMessage());
    }
}
