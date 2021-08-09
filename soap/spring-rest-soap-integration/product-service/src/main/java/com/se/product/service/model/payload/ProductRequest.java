package com.se.product.service.model.payload;

import com.se.product.service.model.CategoriesRequest;
import com.se.product.service.model.PricesRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Valid
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ProductRequest",
        description = "Product item to add")
public class ProductRequest {
    @NotBlank(message = "Product name cannot be empty")
    @Schema(name = "name for the product")
    private String name;

    @Schema(name = "Set of categories for a product")
    private CategoriesRequest categories;

    @Schema(name = "Set of prices for a product")
    private PricesRequest prices;
}
