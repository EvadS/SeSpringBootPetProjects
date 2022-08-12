package com.se.one.to.one.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    private long employeeId;
    private String name;
    private String dept;

    public Employee() {
    }

    public Employee(long employeeId, String name, String dept) {
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

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                '}';
    }
}