package com.sample.rest.server.model;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class Employee {

    @NotEmpty(message = "email must not be empty")
    @Email(message = "email should be a valid email")
    @ApiModelProperty(value = "User registered email", required = true, allowableValues = "mail@mail.com")
    private String email;

    @NotEmpty(message = "first name must not be empty")
    @ApiModelProperty(value = "First name", required = true, allowableValues = "first name")
    private String firstName;

    @NotEmpty(message = "last name must not be empty")
    @ApiModelProperty(value = "Last name", required = true, allowableValues = "last name")
    private String lastName;

    public Employee() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
