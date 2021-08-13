package com.se.product.service.model.search;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;


@Data
@NoArgsConstructor
public class PagedProductSearchRequest {
    // paging
    @Min(0)
    private int page;
    @Min(1)
    private int count;

    private String productName;
    private String costFrom;
    private String costTo;

    private String categoryCode;
    private String categoryName;

    private Long dateFrom;
    private Long dateTo;
}
