package com.se.sample.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
public class BookRequest {
    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be Empty")
    private String title;
    private String author;
    private float price;
}
