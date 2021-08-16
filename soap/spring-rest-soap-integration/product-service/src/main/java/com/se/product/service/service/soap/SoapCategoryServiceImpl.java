package com.se.product.service.service.soap;

import com.se.product.service.model.request.CategoryRequest;
import com.se.product.service.model.response.CategoryResponse;
import com.se.product.service.model.response.CategoryResponseList;
import com.se.product.service.model.search.CategorySearch;
import com.se.product.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Qualifier("soap")
public class SoapCategoryServiceImpl implements CategoryService {
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

    @Override
    public Page<CategoryResponse> search(CategorySearch request, Pageable pageable) {
        return null;
    }
}
