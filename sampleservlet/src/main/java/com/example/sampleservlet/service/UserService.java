package com.example.sampleservlet.service;

import com.example.sampleservlet.dao.UserDao;
import com.example.sampleservlet.model.Offer;
import com.example.sampleservlet.model.User;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public String createUser(User user){
        userDao.saveUser(user);
        return "user saved success";
    }


}
