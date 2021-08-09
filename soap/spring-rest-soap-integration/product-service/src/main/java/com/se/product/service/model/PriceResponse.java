package com.se.product.service.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Provide stored price data")
public class PriceResponse {
    @Schema(name = "id", description = "unique identifier", required = true)
    private long id;

    @Schema(name = "price", description = "Price information", required = true)
    private PriceRequest price;

}
