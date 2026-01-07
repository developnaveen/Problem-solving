package com.example.sampleservlet.servlet;

import com.example.sampleservlet.dao.UserDao;
import com.example.sampleservlet.model.User;
import com.example.sampleservlet.service.UserServiceImpl;
import com.example.sampleservlet.util.Hashing;
import com.example.sampleservlet.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);
    private UserServiceImpl userServiceImpl;
    private final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public void init() {
        UserDao userDao = new UserDao();
        Hashing hashing = new Hashing();
        this.userServiceImpl = new UserServiceImpl(userDao, hashing);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        LOGGER.info("Content-Type: {}", req.getContentType());
        LOGGER.info("User creation request received");

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        try {
            User user = buildUser(req);

            LOGGER.info("Calling userService.createUser()");
            String message = userServiceImpl.createUser(user);

            res.setStatus(HttpServletResponse.SC_CREATED);
            mapper.writeValue(res.getWriter(), Map.of(
                    "status", "SUCCESS",
                    "message", message
            ));

            LOGGER.info("User creation completed successfully");

        } catch (IllegalArgumentException e) {
            LOGGER.warn("Validation failed: {}", e.getMessage());

            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(res.getWriter(), Map.of(
                    "status", "ERROR",
                    "message", e.getMessage()
            ));

        } catch (Exception e) {
            LOGGER.error("Exception while creating user", e);

            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(res.getWriter(), Map.of(
                    "status", "ERROR",
                    "message", "User creation failed"
            ));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        try {
            LOGGER.info("Login request received (GET)");

            String empId = req.getParameter("empId");
            String password = req.getParameter("password");

            boolean isValid = userServiceImpl.valitateUser(empId, password);

            if (!isValid) {
                res.setStatus(HttpServletResponse.SC_OK);
                mapper.writeValue(res.getWriter(), Map.of(
                        "status", "ERROR",
                        "message", "Invalid credentials"
                ));
                return;
            }

            String token = JwtUtil.generateToken(empId);

            res.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(res.getWriter(), Map.of(
                    "status", "SUCCESS",
                    "token", token
            ));

            LOGGER.info("Login successful for empId={}", empId);

        } catch (Exception e) {
            LOGGER.error("Login failed", e);

            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(res.getWriter(), Map.of(
                    "status", "ERROR",
                    "message", "Login failed"
            ));
        }
    }

    private User buildUser(HttpServletRequest req) throws IOException {
        try {
            User user = mapper.readValue(req.getInputStream(), User.class);

            LOGGER.info("User parsed -> email={}, empId={}",
                    user.getEmail(), user.getEmpId());

            return user;

        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JSON body", e);
        }
    }
}
