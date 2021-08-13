package com.se.product.service.domain.specification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchModel {
    private String productCode;
    private String productName;
    private String costFrom;
    private String costTo;

    //Category
    private String categoryCode;
    private String categoryName;

    private Long dateFrom;
    private Long dateTo;


}
