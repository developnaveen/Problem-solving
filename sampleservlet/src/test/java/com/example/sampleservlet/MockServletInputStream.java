package com.example.sampleservlet;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MockServletInputStream extends ServletInputStream {

    private final ByteArrayInputStream inputStream;

    public MockServletInputStream(String content) {
        this.inputStream = new ByteArrayInputStream(content.getBytes());
    }

    @Override
    public boolean isFinished() {
        return inputStream.available() == 0;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener readListener) {
        // not needed for test
    }

    @Override
    public int read() throws IOException {
        return inputStream.read();
    }
}
