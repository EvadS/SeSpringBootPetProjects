package com.se.product.service.service;

import com.se.product.service.model.request.CategoriesRequest;
import com.se.product.service.model.request.PricesRequest;
import com.se.product.service.model.request.ProductRequest;
import com.se.product.service.model.response.ProductItemResponse;
import com.se.product.service.model.response.ProductResponse;
import com.se.product.service.model.search.PagedProductSearchRequest;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductResponse create(ProductRequest product);

    ProductResponse update(Long id, ProductRequest product);

    void delete(Long id);

    ProductResponse changeCategories(Long id, CategoriesRequest categoriesRequest);

    ProductResponse changePrices(Long id, PricesRequest pricesRequest);

    Page<ProductItemResponse> getPaged(PagedProductSearchRequest searchRequest);

    ProductResponse get(Long id);
}
