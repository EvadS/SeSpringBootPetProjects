package com.se.sample.rest.client.model.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmployeeRequest {

    // TODO: simulate constraint exception on server
   // @NotEmpty(message = "email must not be empty")
  //  @Email(message = "email should be a valid email")
    @ApiModelProperty(value = "User registered email", required = true, allowableValues = "mail@mail.com")
    private String email;

    @ApiModelProperty(value = "First name", required = false, allowableValues = "first name")
    private String firstName;

    @NotEmpty(message = "last name must not be empty")
    @ApiModelProperty(value = "Last name", required = true, allowableValues = "last name")
    private String lastName;

    public EmployeeRequest() {
    }


    public EmployeeRequest(@NotEmpty(message = "email must not be empty")
                           String email,
                           @NotEmpty(message = "first name must not be empty") String firstName,
                           @NotEmpty(message = "last name must not be empty") String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
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


    @Override
    public String toString() {
        return "EmployeeRequest{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
