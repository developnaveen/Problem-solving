package com.example.sampleservlet.testutil;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class FakeHttpServletResponse implements HttpServletResponse {

    private int status;
    private final StringWriter writer = new StringWriter();

    public String getBody() {
        return writer.toString();
    }

    @Override
    public PrintWriter getWriter() {
        return new PrintWriter(writer);
    }

    @Override
    public void addCookie(Cookie cookie) {

    }

    @Override
    public boolean containsHeader(String s) {
        return false;
    }

    @Override
    public String encodeURL(String s) {
        return "";
    }

    @Override
    public String encodeRedirectURL(String s) {
        return "";
    }

    @Override
    public void sendError(int i, String s) throws IOException {

    }

    @Override
    public void sendError(int i) throws IOException {

    }

    @Override
    public void sendRedirect(String s) throws IOException {

    }

    @Override
    public void setDateHeader(String s, long l) {

    }

    @Override
    public void addDateHeader(String s, long l) {

    }

    @Override
    public void setHeader(String s, String s1) {

    }

    @Override
    public void addHeader(String s, String s1) {

    }

    @Override
    public void setIntHeader(String s, int i) {

    }

    @Override
    public void addIntHeader(String s, int i) {

    }

    @Override
    public void setStatus(int sc) {
        this.status = sc;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String getHeader(String s) {
        return "";
    }

    @Override
    public Collection<String> getHeaders(String s) {
        return List.of();
    }

    @Override
    public Collection<String> getHeaderNames() {
        return List.of();
    }

    // --- unused methods (safe to ignore for tests) ---
    @Override public void flushBuffer() {}
    @Override public int getBufferSize() { return 0; }
    @Override public String getCharacterEncoding() { return null; }
    @Override public String getContentType() { return null; }
    @Override public Locale getLocale() { return null; }
    @Override public ServletOutputStream getOutputStream() { return null; }
    @Override public boolean isCommitted() { return false; }
    @Override public void reset() {}
    @Override public void resetBuffer() {}
    @Override public void setBufferSize(int size) {}
    @Override public void setCharacterEncoding(String charset) {}
    @Override public void setContentLength(int len) {}
    @Override public void setContentLengthLong(long len) {}
    @Override public void setContentType(String type) {}
    @Override public void setLocale(Locale loc) {}
}
