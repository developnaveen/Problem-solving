package com.example.sampleservlet.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LogingFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info("LoggingFilter initialized");
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        LOGGER.info("New request has come");

        chain.doFilter(request, response);

        LOGGER.info("Response has been sent");
    }

    @Override
    public void destroy() {

    }
}
