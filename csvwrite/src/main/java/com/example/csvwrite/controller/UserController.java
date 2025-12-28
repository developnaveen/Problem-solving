package com.example.csvwrite.controller;

import com.example.csvwrite.dto.RegisterRequest;
import com.example.csvwrite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User APIs", description = "User Registration and Authentication")

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Operation(
            summary = "Register a new user",
            description = "Creates a new user account"
    )
    @ApiResponse(responseCode = "200", description = "User registered successfully")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request ){
        logger.info("Entered into register controller: {}",request);
        userService.registerUser(request);
        logger.info("completed in register controller");
        return ResponseEntity.ok("user registered");
    }

    @Operation(
            summary = "Login user",
            description = "Authenticates user and returns JWT token"
    )
    @ApiResponse(responseCode = "200", description = "Login successful")

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RegisterRequest request){
        logger.info("Entered into login controller: {}",request);
        String token = userService.login(request);
        logger.info("completed in login controller");
        return ResponseEntity.ok(token);
    }
}
