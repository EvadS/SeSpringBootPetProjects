package com.se.demodatetime.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class MyDto {

    private String name;

    @JsonFormat(pattern = "yyyy.MM.dd'T'HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime firstDate;

    @JsonFormat(pattern = "yyyy.MM.dd'T'HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime secondDate;

    public MyDto() {
        this.name = "my name";
        this.firstDate = LocalDateTime.now();
        this.secondDate = LocalDateTime.now().minusDays(1l).minusHours(12l);
    }

    public MyDto(String name, LocalDateTime firstDate, LocalDateTime secondDate) {
        this.name = name;
        this.firstDate = firstDate;
        this.secondDate = secondDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(LocalDateTime firstDate) {
        this.firstDate = firstDate;
    }

    public LocalDateTime getSecondDate() {
        return secondDate;
    }

    public void setSecondDate(LocalDateTime secondDate) {
        this.secondDate = secondDate;
    }

    // constructors, getters, setters
}