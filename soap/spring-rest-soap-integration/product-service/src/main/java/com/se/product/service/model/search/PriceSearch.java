package com.se.product.service.model.search;


import com.se.product.service.model.enums.CurrencyType;
import com.se.product.service.validation.annotation.MyEnumValidator;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;


@Data
@NoArgsConstructor
public class PriceSearch {


    @MyEnumValidator(enumClass = CurrencyType.class)
    private String currencyType;

    private Double priceFrom;
    private Double priceTo;

    @Min(value = 0,message = "The page should be less or or equal 0")
    private int pageNum;

    @Positive
    private int pageSize;
}
