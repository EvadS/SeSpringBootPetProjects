package com.se.product.service.controller.base;

import com.se.product.service.model.CategoryRequest;
import com.se.product.service.model.CategoryResponse;
import com.se.product.service.model.CategoryResponseList;
import com.se.product.service.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public CategoryResponse create(CategoryRequest request) {
        return null;
    }

    @Override
    public CategoryResponse updateItem(Long id, CategoryRequest requestModel) {
        return null;
    }

    @Override
    public CategoryResponse changeBase(Long id, Long baseId) {
        return null;
    }

    @Override
    public void deletePrice(Long id) {

    }

    @Override
    public CategoryResponse getById(Long id) {
        return null;
    }

    @Override
    public CategoryResponseList getAll() {
        return null;
    }
}
