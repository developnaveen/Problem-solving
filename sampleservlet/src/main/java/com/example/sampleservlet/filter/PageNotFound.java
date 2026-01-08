package com.example.sampleservlet.filter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class PageNotFound extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        String json = """
                {
                    "status": 404,
                    "error": "not found",
                    "message": "Request api not found",
                    "path": "%s"
                }    
                """.formatted(req.getRequestURI());
                res.getWriter().write(json);
    }
}
