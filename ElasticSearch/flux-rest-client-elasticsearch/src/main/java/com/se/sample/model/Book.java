package com.se.sample.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Book {
    private String id;
    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be Empty")
    private String title;
    private String author;
    private float price;

}
