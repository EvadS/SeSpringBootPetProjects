package com.se.sample.models.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(title = "Provide product full information model")
public class ProductResponse {
    @Schema(title = "unique identifier")
    private String id;

    @Schema(title = "price of product")
    private Double price;

    @Schema(title = "category of product")
    private String category;
}
