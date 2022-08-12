package com.sample.rest.server.model.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@ApiModel(description = "Provide specification on the model to manipulate employe.")
public class EmployeeVO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @ApiModelProperty(value = "unique user identifier", required = true, allowableValues = "1")
    private Long employeeId;

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

    public EmployeeVO(Long id, String firstName, String lastName, String email) {
        super();
        this.employeeId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public EmployeeVO() {
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
  }
