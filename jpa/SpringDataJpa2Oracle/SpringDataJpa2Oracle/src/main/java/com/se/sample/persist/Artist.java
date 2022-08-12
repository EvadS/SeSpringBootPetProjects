package com.se.sample.persist;


import java.io.Serializable;

public class Artist implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
