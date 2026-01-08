package com.example.sampleservlet.servlet;

import com.example.sampleservlet.model.User;
import com.example.sampleservlet.service.UserServiceImpl;
import com.example.sampleservlet.testutil.FakeHttpServletRequest;
import com.example.sampleservlet.testutil.FakeHttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LoginServletTest {

    private LoginServlet servlet;
    private FakeHttpServletRequest request;
    private FakeHttpServletResponse response;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() throws Exception {
        servlet = new LoginServlet();

        // init first (creates real service)
        servlet.init(mock(ServletConfig.class));

        // THEN override with mock
        UserServiceImpl mockService = mock(UserServiceImpl.class);

        var field = LoginServlet.class.getDeclaredField("userServiceImpl");
        field.setAccessible(true);
        field.set(servlet, mockService);

        userService = mockService;

        request = new FakeHttpServletRequest();
        response = new FakeHttpServletResponse();

        System.setProperty("test.env", "true");
    }


    @Test
    void shouldCreateUserSuccessfully() throws Exception {
        // Arrange
        User user = new User();
        user.setEmpId("EMP1");
        user.setPassword("pass");

        String json = new ObjectMapper().writeValueAsString(user);
        request.setBody(json);

        when(userService.createUser(any(User.class)))
                .thenReturn("user saved success");

        // Act
        servlet.doPost(request, response);

        // Assert
        assertEquals(HttpServletResponse.SC_CREATED, response.getStatus());

        String body = response.getBody();
        assertTrue(body.contains("SUCCESS"));
        assertTrue(body.contains("user saved success"));
    }

    @Test
    void shouldValidateUserSuccessfully() throws Exception {
        // Arrange
        request.setParameter("empId", "EMP1");
        request.setParameter("password", "pass");

        when(userService.valitateUser("EMP1", "pass"))
                .thenReturn(true);

        // Act
        servlet.doGet(request, response);

        // Assert
        assertEquals(HttpServletResponse.SC_OK, response.getStatus());

        String body = response.getBody();
        assertTrue(body.contains("\"status\":\"SUCCESS\""));
        assertTrue(body.contains("\"token\""));
    }
}
