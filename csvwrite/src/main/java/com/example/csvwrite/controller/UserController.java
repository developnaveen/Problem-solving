package com.example.csvwrite.controller;

import com.example.csvwrite.dto.RegisterRequest;
import com.example.csvwrite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request ){
        userService.registerUser(request);
        return ResponseEntity.ok("user registered");
    }


    @GetMapping("/login")
    public ResponseEntity login(){
        userService.loging();
        return ResponseEntity.ok("user logged success fully");
    }
}
