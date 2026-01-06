package com.example.sampleservlet.service;

import com.example.sampleservlet.dao.UserDao;
import com.example.sampleservlet.model.User;
import com.example.sampleservlet.util.Hashing;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class UserServiceImplTest {

    static class FakeUserDao extends UserDao {
        boolean saved = false;

        @Override
        public void saveUser(User user) {
            saved = true;
        }

        @Override
        public String getUser(String username) {
            return "hashedPassword";
        }
    }

    static class FakeHashing extends Hashing {
        @Override
        public String hashData(String data) {
            return "hashedPassword";
        }

        @Override
        public boolean verifyPassword(String data, String password) {
            return true;
        }
    }

    @Test
    void shouldCreateUser() {
        FakeUserDao dao = new FakeUserDao();
        FakeHashing hashing = new FakeHashing();

        UserServiceImpl service = new UserServiceImpl(dao, hashing);

        User user = new User();
        user.setEmpId("EMP1");
        user.setPassword("plain");

        String result = service.createUser(user);

        assertEquals("user saved success", result,"success");
        assertTrue(dao.saved);
        assertEquals("hashedPassword", user.getPassword(),"success");
    }
}
