package com.se.one.to.one.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Task {
    @EmbeddedId
    private CompositeTaskId taskId;

    @MapsId("employeeKey2")
    @OneToOne
    private Employee employee;

    private String taskName;

    public Task() {
    }

    public Task(CompositeTaskId taskId, Employee employee) {
        this.taskId = taskId;
        this.employee = employee;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }



    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", employee=" + employee +
                ", taskName='" + taskName + '\'' +
                '}';
    }
}