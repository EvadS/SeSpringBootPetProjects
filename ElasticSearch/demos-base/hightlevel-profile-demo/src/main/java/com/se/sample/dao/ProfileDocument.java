package com.se.sample.dao;

import lombok.Data;

import java.util.List;

@Data
public class ProfileDocument {

    private String id;
    private String firstName;
    private String lastName;
    private List<Technologies> technologies;
    private List<String> emails;
}