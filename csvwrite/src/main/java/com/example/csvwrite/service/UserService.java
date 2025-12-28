package com.example.csvwrite.service;

import com.example.csvwrite.dto.RegisterRequest;
import com.example.csvwrite.repository.UserRepository;
import com.example.csvwrite.springsecurityconfig.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private Logger log = LoggerFactory.getLogger(UserService.class);

    public Boolean registerUser(RegisterRequest request){

        log.info("Enterd into registerUser");
        if (userRepository.isUserExists(request.getName())) {
            throw new RuntimeException("User already exists");
        }

        String hashpassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(hashpassword);

        int rows = userRepository.saveUser(request);

        log.info("user has been saved successfully");
        return rows == 1;
    }

    public String login(RegisterRequest request){
        String dbPassword = userRepository.findPasswordByUser(request.getName());
        log.info("Entered into login");
        if (dbPassword == null) {
            throw new RuntimeException("User not found");
        }

        if (!passwordEncoder.matches(request.getPassword(),dbPassword)) {
            throw new RuntimeException("Invalid credentials");
        }
        log.info("user has been logged in successfully");
        return JwtUtil.generateToken(request.getName());
    }


}
