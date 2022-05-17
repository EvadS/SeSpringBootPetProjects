package com.se.services.elasticsearch.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class Book {

    private String id;
    private String title;
    private String author;
    private float price;

    //standard setters and getters
}