package com.se.product.service.service;

import com.se.product.service.model.CategoriesRequest;
import com.se.product.service.model.PricesRequest;
import com.se.product.service.model.ProductItemResponse;
import com.se.product.service.model.payload.ProductRequest;
import com.se.product.service.model.payload.ProductResponse;
import com.se.product.service.model.payload.ProductSearchRequest;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;

public interface ProductService {
    ProductResponse create(ProductRequest product);

    ProductResponse update(Long id, ProductRequest product);

    void delete(Long id);

    ProductResponse updateCategories(Long id, CategoriesRequest categoriesRequest);

    ProductResponse updatePrices(Long id, PricesRequest pricesRequest);

    Page<ProductItemResponse> getPagged(ProductSearchRequest searchRequest);

}
