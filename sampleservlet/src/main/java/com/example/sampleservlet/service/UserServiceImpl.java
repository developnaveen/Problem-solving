package com.example.sampleservlet.service;

import com.example.sampleservlet.dao.UserDao;
import com.example.sampleservlet.model.User;
import com.example.sampleservlet.util.Hashing;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final Hashing hashing;

    public UserServiceImpl(UserDao userDao, Hashing hashing) {
        this.userDao = userDao;
        this.hashing = hashing;
    }

    public String createUser(User user) {
        String hashedPassword = hashing.hashData(user.getPassword());
        user.setPassword(hashedPassword);
        userDao.saveUser(user);
        return "user saved success";
    }

    public Boolean valitateUser(String username, String password) {
        String dbPassword = userDao.getUser(username);
        if (hashing.verifyPassword(password, dbPassword)) {
            return true;
        }
        return false;
    }
}
