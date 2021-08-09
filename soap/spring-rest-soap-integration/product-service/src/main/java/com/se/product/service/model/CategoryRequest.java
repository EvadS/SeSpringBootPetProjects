package com.se.product.service.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Schema(description = "Provide model to create new category item")
public class CategoryRequest {

    @Schema(name = "name", description = "category name",
            required = true)
    private String name;

    @Schema(name = "code", description = "category code",
            required = true)
    private String code;

    @Schema(name = "baseCategory", description = "base category id. Null when category don't base")
    private Long baseCategory;
}
