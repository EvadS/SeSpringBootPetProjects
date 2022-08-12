package com.se.sample.employeecrud.entity;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

import javax.persistence.GenerationType;


@Entity
@Table(name = "Emp")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "first_name", nullable = false)
    private String fname;
    @Column(name = "last_name", nullable = false)
    private String lname;
    @Column(name = "email_address", nullable = false)
    private String email;

    public Employee() {

    }

    public Employee(String firstName, String lastName, String emailId) {
        this.fname= firstName;
        this.lname= lastName;
        this.email= emailId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", first-Name=" + fname+ ", last-Name=" + lname+ ", email-Id=" + email
                + "]";
    }

}