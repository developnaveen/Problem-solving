package com.example.sampleservlet.service;

import com.example.sampleservlet.model.User;

public interface UserService {

    public String createUser(User user);
    public String valitateUser(String username, String passwprd);
}
