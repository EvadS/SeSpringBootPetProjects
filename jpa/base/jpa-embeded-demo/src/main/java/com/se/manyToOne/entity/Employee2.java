package com.se.manyToOne.entity;

import javax.persistence.*;

@Entity
public class Employee2 {
    @Id
    private long employeeId;
    private String name;
    private String dept;

    public Employee2() {
    }

    public Employee2(long employeeId, String name, String dept) {
        this.employeeId = employeeId;
        this.name = name;
        this.dept = dept;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}