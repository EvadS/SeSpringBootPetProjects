package com.se.task.demo.entity;


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

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
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