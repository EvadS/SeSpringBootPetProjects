package com.se.manyToOne.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CompositeTaskId2 implements Serializable {
    private long employeeKey;
    private long taskId;

    public CompositeTaskId2() {
    }

    public CompositeTaskId2(long employeeId, long taskId) {
        this.employeeKey = employeeId;
        this.taskId = taskId;
    }

    public long getEmployeeKey() {
        return employeeKey;
    }

    public void setEmployeeKey(long employeeKey) {
        this.employeeKey = employeeKey;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }
}