package com.se.product.service.model.request;

import com.se.product.service.model.enums.CurrencyType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Schema(name = "PriceRequest",
        description = "Price item to add")
public class PriceRequest {

    @DecimalMin(value = "0.01", message = "The price const must be more zero")
    @Schema(name = "cost", description = "price value",
            required = true, example = "0.01")
    private double cost;

    @NotNull
    @Schema(name = "currencyType",
            description = "Currency type.",
            required = true, allowableValues = "UAH, RUB, USD, EUR")
    private CurrencyType currencyType;
}
