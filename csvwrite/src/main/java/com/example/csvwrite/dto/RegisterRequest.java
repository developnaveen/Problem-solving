package com.example.csvwrite.dto;
import io.swagger.v3.oas.annotations.media.Schema;

public class RegisterRequest {
    @Schema(example = "naveen", description = "Username")
    private String name;
    @Schema(example = "P@ssw0rd", description = "User password")
    private String password;

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
