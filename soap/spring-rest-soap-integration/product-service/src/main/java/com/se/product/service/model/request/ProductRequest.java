package com.se.product.service.model.request;

import com.se.product.service.model.request.CategoriesRequest;
import com.se.product.service.model.request.PricesRequest;
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
    @Schema(name ="name",description= "name for the product")
    private String name;

    @Schema(name = "categories", description = "Set of categories for a product")
    private CategoriesRequest categories;

    @Schema(name ="prices", description = "Set of prices for a product")
    private PricesRequest prices;
}
