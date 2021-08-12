package com.se.product.service.model.search;


import com.se.product.service.model.enums.CurrencyType;
import com.se.product.service.validation.annotation.ValueOfEnum;
import com.se.product.service.validation.validator.ValueOfEnumValidator;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;


@Data
@NoArgsConstructor
public class PriceSearch {

    @ValueOfEnum(enumClass = CurrencyType.class)
    private CurrencyType currencyType;

    private Double priceFrom;
    private Double priceTo;

    @Min(value = 0,message = "The page should be less or or equal 0")
    private int pageNum;

    @Positive
    private int pageSize;
}
