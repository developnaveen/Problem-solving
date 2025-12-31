package com.example.sampleservlet.servlet;

import com.example.sampleservlet.dao.UserDao;
import com.example.sampleservlet.model.User;
import com.example.sampleservlet.service.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() {
        UserDao userDao = new UserDao();
        this.userService = new UserService(userDao);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        try {
            User user = buildUser(req);

            String message = userService.createUser(user);

            res.setStatus(HttpServletResponse.SC_CREATED);
            res.getWriter().write("""
                {
                  "status": "SUCCESS",
                  "message": "%s"
                }
                """.formatted(message));

        } catch (Exception e) {

            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            res.getWriter().write("""
                {
                  "status": "ERROR",
                  "message": "User creation failed"
                }
                """);
        }
    }

    private User buildUser(HttpServletRequest req) {

        User user = new User();
        user.setEmail(req.getParameter("email"));
        user.setEmpId(req.getParameter("empId"));
        user.setPassword(req.getParameter("password"));

        return user;
    }
}
