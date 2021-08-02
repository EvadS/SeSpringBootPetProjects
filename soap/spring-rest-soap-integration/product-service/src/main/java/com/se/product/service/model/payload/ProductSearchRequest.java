package com.se.product.service.model.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;


@Data
@NoArgsConstructor
public class ProductSearchRequest {
    private String price;

    @Min(0)
    private int page;
    @Min(1)
    private int count;
}
