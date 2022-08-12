package com.se.sample.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.se.sample.entity.Account;

public class AccountResponse {
    public Long id;
    public String username;

    public AccountResponse() {

    }

    public AccountResponse(Account account) {
        this.id = account.id;
        this.username = account.username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
