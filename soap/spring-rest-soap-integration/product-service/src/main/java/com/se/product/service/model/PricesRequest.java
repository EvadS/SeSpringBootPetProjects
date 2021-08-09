package com.se.product.service.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "PricesRequest",
        description = "Provide base information about product prices")
public class PricesRequest {
    @Schema(name = "Set of prices ids")
    private Set<Long> prices = new HashSet<>();
}
