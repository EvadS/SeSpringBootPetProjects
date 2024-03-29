package com.se.product.service.model.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CategoriesRequest",
        description = "Provide base information about product categories")
public class CategoriesRequest {
    @Schema(name="categories", description= "Set of categories ids")
    private Set<Long> categories;
}
