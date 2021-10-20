package com.se.sample.models.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Provide product model")
public class ProductRequest {

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be Empty")
    @Schema(description = "Name of the product.",
            example = "San Angeles", required = true)
    private String name;

    @NotNull(message = "Quantity can't be null")
    @Schema(description = "Price of the product.",
            example = "0.001", required = true)
    @DecimalMin(value = "0.0", inclusive = false, message = "Price should be greater than zero")
    private Double price;

    @NotNull(message = "Quantity can't be null")
    @Digits(message = "Quantity should be be digit", integer = 1, fraction = 0)
    @Min(value = 1, message = "From must be greater than zero")
    @Schema(description = "Quantity of the product.",
            example = "1", required = true)
    private Integer quantity;

    @Schema(description = "Category of the product",
            example = "category", required = false)
    private String category;

    @Schema(description = "Description of the product",
            example = "Description", required = false)
    private String description;

    @Schema(description = "Manufacturer of the product",
            example = "Manufacturer", required = false)
    private String manufacturer;
}
