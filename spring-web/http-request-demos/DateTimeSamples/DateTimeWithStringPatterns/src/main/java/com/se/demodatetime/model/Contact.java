package com.se.demodatetime.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Contact {

    // other fields

    @JsonFormat(pattern="yyyy-MM")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDate birthday;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime changeDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdate;

    public Contact() {
        this.birthday = LocalDate.now();
        this.lastUpdate = LocalDateTime.now();this.changeDate = LocalDateTime.now();
    }

    public Contact(LocalDate birthday, LocalDateTime lastUpdate) {
        this.birthday = birthday;
        this.lastUpdate = lastUpdate;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public LocalDateTime getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDateTime changeDate) {
        this.changeDate = changeDate;
    }

// standard getters and setters

}