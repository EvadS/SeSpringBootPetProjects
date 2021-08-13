package com.se.product.service.model.search;

import com.se.product.service.model.enums.CurrencyType;
import com.se.product.service.validation.annotation.MyEnumValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@Schema(name = "PriceSearchRequest",
        description = "Provide model to search price")
public class PriceSearchRequest {

    @MyEnumValidator(enumClass = CurrencyType.class)
    @Schema(name = "currencyType", description = "currency type for search")
    private String currencyType;

    @Schema(name = "priceTo", description = "minimum price range")
    private Double priceFrom;

    @Schema(name = "priceFrom", description = "minimum price range")
    private Double priceTo;

    @Min(value = 0, message = "The page should be less or or equal 0")
    @Schema(name = "pageNum", description = "Page zero-based page index")
    private int pageNum;

    @Positive
    @Schema(name = "pageSize",
            description = "the size of elements in the page to be returned")
    private int pageSize;
}
