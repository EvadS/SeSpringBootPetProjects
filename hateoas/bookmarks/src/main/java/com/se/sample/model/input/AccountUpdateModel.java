package com.se.sample.model.input;

import javax.validation.constraints.NotEmpty;

public class AccountUpdateModel {

    public Long id;

    @NotEmpty
    public String password;

    @NotEmpty
    public String username;

    public AccountUpdateModel() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
