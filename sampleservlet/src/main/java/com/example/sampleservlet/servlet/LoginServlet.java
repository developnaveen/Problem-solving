package com.example.sampleservlet.servlet;

import com.example.sampleservlet.dao.UserDao;
import com.example.sampleservlet.model.User;
import com.example.sampleservlet.service.UserServiceImpl;
import com.example.sampleservlet.util.Hashing;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

@WebServlet("/user")
public class LoginServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);
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
        log.info("Content-Type: {}", req.getContentType());
        log.info("User creation request received");

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        try {
            User user = buildUser(req);

            log.info("Calling userService.createUser()");
            String message = userServiceImpl.createUser(user);

            res.setStatus(HttpServletResponse.SC_CREATED);
            mapper.writeValue(res.getWriter(), Map.of(
                    "status", "SUCCESS",
                    "message", message
            ));

            log.info("User creation completed successfully");

        } catch (IllegalArgumentException e) {
            log.warn("Validation failed: {}", e.getMessage());

            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(res.getWriter(), Map.of(
                    "status", "ERROR",
                    "message", e.getMessage()
            ));

        } catch (Exception e) {
            log.error("Exception while creating user", e);

            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(res.getWriter(), Map.of(
                    "status", "ERROR",
                    "message", "User creation failed"
            ));
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
         try{
             log.info("Entered in to login url of get");

             String message = userServiceImpl.valitateUser(req.getParameter("empId"), req.getParameter("password"));

             res.setStatus(HttpServletResponse.SC_CREATED);
             mapper.writeValue(res.getWriter(), Map.of(
                     "status", "SUCCESS",
                     "message", message
             ));
             log.info("Steped of login url of get");
         } catch (Exception e) {
             log.error("Exception while loging user", e);

             res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
             mapper.writeValue(res.getWriter(), Map.of(
                     "status", "ERROR",
                     "message", "user credential is wrong"
             ));
         }

    }

    private User buildUser(HttpServletRequest req) throws IOException {
        try {
            User user = mapper.readValue(req.getInputStream(), User.class);

            log.info("User parsed -> email={}, empId={}",
                    user.getEmail(), user.getEmpId());

            return user;

        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JSON body", e);
        }
    }
}
