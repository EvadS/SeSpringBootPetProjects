package com.se.product.service.model.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Schema(name = "CategorySearch",
        description = "Provide model to category search")
public class CategorySearch {

    @Schema(name = "code",
            description = "Category unique code")
    private String code;
    @Schema(name = "name",
            description = "Category name")
    private String name;
}
