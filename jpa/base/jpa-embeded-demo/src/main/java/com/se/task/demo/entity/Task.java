package com.se.task.demo.entity;

//mport javax.persistence.Entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@IdClass(CompositeTaskId.class)
public class Task {
    @Id
    private long taskId;
    @Id
    @OneToOne
    private Employee employee;
    private String taskName;
    private Date date;

    public Task() {
    }

    public Task(Employee employee, long taskId) {
        this.employee = employee;
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", employee=" + employee +
                ", taskName='" + taskName + '\'' +
                ", date=" + date +
                '}';
    }
}