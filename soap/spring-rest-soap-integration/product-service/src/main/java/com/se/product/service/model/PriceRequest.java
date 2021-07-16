package com.se.product.service.model;

import com.se.product.service.model.enums.CurrencyType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ApiModel(description = "Provide model to create new price item")
public class PriceRequest {
    @Min(0)
    @ApiModelProperty(name = "cost", notes = "price value",
            required = true)
    private double cost;

    @NotNull
    @ApiModelProperty(notes = "Currency type.",
            required = true, allowableValues = "UAH, RUB, USD, EUR")
    private CurrencyType currencyType;
}
