package com.example.spring.cloud.stream.models;

public class Person {

    private String name;
    private boolean completed;

    public Person() {
    }
    public Person(String name) {
        this.setName(name);
        this.completed = true;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}