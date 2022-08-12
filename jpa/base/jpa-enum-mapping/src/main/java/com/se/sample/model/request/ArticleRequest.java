package com.se.sample.model.request;

import com.se.sample.model.enums.Category;
import com.se.sample.model.enums.Type;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Provide article to store in data base")
public class ArticleRequest {
    @NotBlank
    private String title;

    @NotNull
    @ApiModelProperty(notes = "EnumType.ORDINAL of Status enums",
            required = true, allowableValues = "0,1,2,3")
    private int status;

    @NotNull
    @ApiModelProperty(notes = "Type of article. String enum demo",
            required = true, allowableValues = "INTERNAL, EXTERNAL")
    private Type type;

    @NotNull
    @ApiModelProperty(notes = "Priority of article. @Transient demo",
            required = true, allowableValues = "100, 200,300")
    private int priority;

    @NotNull
    @ApiModelProperty(notes = "Category. @CategoryConverter demo",
            required = true, allowableValues = "S, M, T")
    private Category category;

    public ArticleRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
