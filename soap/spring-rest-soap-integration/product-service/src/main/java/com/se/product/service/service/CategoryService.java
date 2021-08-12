package com.se.product.service.service;

import com.se.product.service.model.request.CategoryRequest;
import com.se.product.service.model.response.CategoryResponse;
import com.se.product.service.model.response.CategoryResponseList;
import com.se.product.service.model.search.CategorySearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    /**
     * create new category
     * @param request category model to create
     * @return
     */
    CategoryResponse create(CategoryRequest request);

    CategoryResponse updateItem(Long id, CategoryRequest requestModel);

    CategoryResponse changeBase(Long id, Long baseId);

    void deletePrice(Long id);

    CategoryResponse getById(Long id);

    CategoryResponseList getAll();

    Page<CategoryResponse> search(CategorySearch request, Pageable pageable);
}
