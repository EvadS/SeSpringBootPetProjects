package com.se.sample.rest.client.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@ApiModel(value = "Task", description = "A user task")
public class Tutorial {

    @ApiModelProperty(value = "The unique identifier of the given task", readOnly = true)

    private long id;

    @ApiModelProperty(value = "Title of the task", required = true)
    @NotNull
    @Size(min = 1, max = 64)
    private String title;

    @ApiModelProperty(value = "Description of the task", required = true)
    @NotNull
    @Size
    private String description;

    private boolean published;

    public Tutorial() {

    }

    public Tutorial(String title, String description, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean isPublished) {
        this.published = isPublished;
    }

    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
    }

}