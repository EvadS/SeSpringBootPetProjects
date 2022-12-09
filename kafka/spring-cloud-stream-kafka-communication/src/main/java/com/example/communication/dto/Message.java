package com.example.communication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;


public class Message {
    private String name;

    public Message() {
    }

    public Message(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(name, message.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

