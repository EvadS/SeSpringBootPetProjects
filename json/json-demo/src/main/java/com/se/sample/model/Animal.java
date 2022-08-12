package com.se.sample.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by Evgeniy Skiba on 19.03.21
 */
@JsonDeserialize(as = Cat.class)
public  abstract class Animal {
    public String name;

    public Animal() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
