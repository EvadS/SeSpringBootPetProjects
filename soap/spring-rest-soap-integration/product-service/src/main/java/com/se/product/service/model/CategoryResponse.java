package com.se.product.service.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Provide stored category data")
public class CategoryResponse {
    @Schema(name = "id", description = "unique identifier", required = true)
    private long id;

    @NotNull(message = "category is required field")
    @Schema(name = "categoryRequest", description = "Category information", required = true)
    private CategoryRequest categoryRequest;
}

