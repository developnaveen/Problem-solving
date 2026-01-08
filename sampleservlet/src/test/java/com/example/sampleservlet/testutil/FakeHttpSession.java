package com.example.sampleservlet.testutil;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class FakeHttpSession implements HttpSession {

    private final Map<String, Object> attributes = new HashMap<>();

    @Override
    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return null;
    }

    @Override
    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        attributes.remove(name);
    }

    // ---- Required minimal methods ----

    @Override
    public String getId() {
        return "FAKE_SESSION";
    }

    @Override
    public long getCreationTime() {
        return 0;
    }

    @Override
    public long getLastAccessedTime() {
        return 0;
    }

    @Override
    public void invalidate() {
        attributes.clear();
    }

    @Override
    public boolean isNew() {
        return false;
    }

    // ---- Unused but required by interface ----

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public void setMaxInactiveInterval(int interval) {}

    @Override
    public int getMaxInactiveInterval() {
        return 0;
    }
}
