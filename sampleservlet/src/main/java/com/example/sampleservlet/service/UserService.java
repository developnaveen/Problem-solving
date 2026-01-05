package com.example.sampleservlet.service;

import com.example.sampleservlet.model.User;

public interface UserService {

    public String createUser(User user);
    public Boolean valitateUser(String username, String passwprd);
}
