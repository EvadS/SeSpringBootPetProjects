package com.se.sample.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
public class AlarmLevel {
    @Id
    @GeneratedValue
    private long id;


}
