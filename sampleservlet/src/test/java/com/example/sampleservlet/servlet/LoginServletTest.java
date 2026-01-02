package com.example.sampleservlet.servlet;

import com.example.sampleservlet.model.User;
import com.example.sampleservlet.service.UserServiceImpl;
import com.example.sampleservlet.testutil.FakeHttpServletRequest;
import com.example.sampleservlet.testutil.FakeHttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginServletTest {

    private LoginServlet servlet;
    private FakeHttpServletRequest request;
    private FakeHttpServletResponse response;

    static class FakeUserService extends UserServiceImpl {
        FakeUserService() {
            super(null, null);
        }

        @Override
        public String createUser(User user) {
            return "user saved success";
        }

        @Override
        public String valitateUser(String username, String password) {
            return "user exit";
        }
    }

    @BeforeEach
    void setUp() throws Exception {
        servlet = new LoginServlet();


        var field = LoginServlet.class.getDeclaredField("userServiceImpl");
        field.setAccessible(true);
        field.set(servlet, new FakeUserService());

        request = new FakeHttpServletRequest();
        response = new FakeHttpServletResponse();
    }

    @Test
    void shouldCreateUserSuccessfully() throws Exception {
        User user = new User();
        user.setEmpId("EMP1");
        user.setPassword("pass");

        String json = new ObjectMapper().writeValueAsString(user);
        request.setBody(json);

        servlet.doPost(request, response);

        assertEquals(HttpServletResponse.SC_CREATED, response.getStatus());
        assertTrue(response.getBody().contains("SUCCESS"));
        assertTrue(response.getBody().contains("user saved success"));
    }

    @Test
    void shouldValidateUserSuccessfully() throws Exception {
        request.setParameter("empId", "EMP1");
        request.setParameter("password", "pass");

        servlet.doGet(request, response);

        assertEquals(HttpServletResponse.SC_CREATED, response.getStatus());
        assertTrue(response.getBody().contains("SUCCESS"));
        assertTrue(response.getBody().contains("user exit"));
    }
}
