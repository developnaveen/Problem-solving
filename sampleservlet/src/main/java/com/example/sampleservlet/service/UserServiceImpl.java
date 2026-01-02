package com.example.sampleservlet.service;

import com.example.sampleservlet.dao.UserDao;
import com.example.sampleservlet.model.User;
import com.example.sampleservlet.util.Hashing;


public class UserServiceImpl implements UserService{
    private final UserDao userDao;

    public Hashing hs = new Hashing();
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    public String createUser(User user){
        String hspassword = hs.hashData(user.getPassword());
        user.setPassword(hspassword);
        userDao.saveUser(user);
        return "user saved success";
    }

    public String valitateUser(String username, String passwprd){
        String dbpassword = userDao.getUser(username);
        if(hs.verifyPassword(passwprd, dbpassword)){
            return "user exit";
        }
        return "user not exit";
    }
}
