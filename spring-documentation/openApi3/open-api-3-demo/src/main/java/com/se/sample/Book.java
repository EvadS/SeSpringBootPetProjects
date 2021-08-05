package com.se.sample;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Book {

    @Schema(description = "Unique identifier of the Contact.",
            example = "1", required = true)
    private long id;

    @NotBlank
    @Size(min = 0, max = 20)
    private String title;

    @Schema(description = "Name of the contact.",
            example = "Jessica Abigail", required = true)
    @NotBlank
    @Size(max = 100)
    private String author;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}