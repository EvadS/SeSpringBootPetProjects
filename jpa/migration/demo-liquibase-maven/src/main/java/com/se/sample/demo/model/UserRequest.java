package com.se.sample.demo.model;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Created by Evgeniy Skiba on 01.04.21
 */
public class UserRequest {

    @NotBlank(message = "name is required field ")
    private String userName;

    @Min(value = 1 )
    private int age;

    public UserRequest() {
    }

    public UserRequest(@NotBlank(message = "name is required field ") String userName, @Min(value = 1) int age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
