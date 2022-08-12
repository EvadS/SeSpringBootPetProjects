package com.se.sample.model.input;

import javax.validation.constraints.NotEmpty;

public class AccountModel {


    @NotEmpty
    public String password;

    @NotEmpty
    public String username;

    public AccountModel() {

    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
