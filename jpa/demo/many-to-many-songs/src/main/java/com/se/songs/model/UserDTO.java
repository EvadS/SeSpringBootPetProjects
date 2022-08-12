package com.se.songs.model;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private String name;
    private int role;

    public UserDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}