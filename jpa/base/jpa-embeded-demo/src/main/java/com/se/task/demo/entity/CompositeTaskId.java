package com.se.task.demo.entity;


import java.io.Serializable;
import java.util.Objects;

public class CompositeTaskId implements Serializable {
    //the names of the both fields should be same as the @Id fields in source class
    //the type of 'taskId' field is same as of the basic @Id field defined in the Task entity
    private long taskId;
    //the type of 'employee' field is same as of the @Id field defined in Employee entity.
    private long employee;

    public CompositeTaskId() {
    }

    public CompositeTaskId(long employeeId, long taskId) {
        this.employee = employeeId;
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeTaskId taskId1 = (CompositeTaskId) o;
        if (employee != taskId1.employee) return false;
        return taskId == taskId1.taskId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, taskId);
    }
}