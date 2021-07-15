package com.se.prooduct.service.model.payload;

import com.se.prooduct.service.validation.annotation.NullOrNotBlank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "Login Request", description = "The login request payload")
public class LoginRequest {

    @NullOrNotBlank(message = "Login Username can be null but not blank")
    @ApiModelProperty(value = "Registered username", allowableValues = "NonEmpty String", required = true)
    private String username;

    @NullOrNotBlank(message = "Login Email can be null but not blank")
    @ApiModelProperty(value = "User registered email", required = true, allowableValues = "NonEmpty String")
    private String email;

    @NotNull(message = "Login password cannot be blank")
    @ApiModelProperty(value = "Valid user password", required = true, allowableValues = "NonEmpty String")
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