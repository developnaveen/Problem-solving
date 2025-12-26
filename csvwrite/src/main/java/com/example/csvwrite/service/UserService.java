package com.example.csvwrite.service;

import com.example.csvwrite.dto.RegisterRequest;
import com.example.csvwrite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Boolean registerUser(RegisterRequest request){
        String password = request.getPassword();


        int rows = userRepository.saveUser(request);

        if( rows == 1){
            return false;
        }
        return true;
    }

    public String loging(){

    }


}
