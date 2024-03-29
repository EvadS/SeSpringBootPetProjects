package com.se.product.service.model.request;

import com.se.product.service.validation.annotation.NullOrNotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(name = "LoginRequest", description = "The login request payload")
public class LoginRequest {

    @NullOrNotBlank(message = "Login Username can be null but not blank")
    @Schema(name = "Registered username", allowableValues = "NonEmpty String", required = true)
    private String username;

    @NullOrNotBlank(message = "Login Email can be null but not blank")
    @Schema(name = "User registered email", required = true, allowableValues = "NonEmpty String")
    private String email;

    @NotNull(message = "Login password cannot be blank")
    @Schema(name = "Valid user password", required = true, allowableValues = "NonEmpty String")
    private String password;


    public LoginRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public LoginRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}