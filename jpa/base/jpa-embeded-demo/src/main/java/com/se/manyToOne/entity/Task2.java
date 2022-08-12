package com.se.manyToOne.entity;

import javax.persistence.*;


@Entity
public class Task2 {
    @EmbeddedId
    private CompositeTaskId2 taskId;

    @MapsId("employeeKey")
    @ManyToOne
    private Employee2 employee;

    private String taskName;

    public Task2() {
    }

    public Task2(CompositeTaskId2 taskId, Employee2 employee) {
        this.taskId = taskId;
        this.employee = employee;
    }

    public CompositeTaskId2 getTaskId() {
        return taskId;
    }

    public void setTaskId(CompositeTaskId2 taskId) {
        this.taskId = taskId;
    }

    public Employee2 getEmployee() {
        return employee;
    }

    public void setEmployee(Employee2 employee) {
        this.employee = employee;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

}