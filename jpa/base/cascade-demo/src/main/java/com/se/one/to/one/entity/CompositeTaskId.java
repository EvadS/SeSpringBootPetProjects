package com.se.one.to.one.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompositeTaskId implements Serializable {
    private long employeeKey;
    private long taskId;

    public CompositeTaskId() {
    }

    public CompositeTaskId(long employeeId, long taskId) {
        this.employeeKey = employeeId;
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeTaskId taskId1 = (CompositeTaskId) o;
        if (employeeKey != taskId1.employeeKey) return false;
        return taskId == taskId1.taskId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeKey, taskId);
    }

    @Override
    public String toString() {
        return "CompositeTaskId{" +
                "employeeKey=" + employeeKey +
                ", taskId=" + taskId +
                '}';
    }

}