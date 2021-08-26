package com.se.product.service.service.adaptee.impl;

import com.se.product.service.mapper.SoapCategoryMapper;
import com.se.product.service.model.request.CategoryRequest;
import com.se.product.service.model.response.CategoryResponse;
import com.se.product.service.model.soap.*;
import com.se.product.service.service.CategoryService;
import com.se.product.service.service.adaptee.CategoryAdaptee;
import org.springframework.stereotype.Service;

@Service
public class CategoryAdapter implements CategoryAdaptee {

    private final CategoryService categoryService;

    public CategoryAdapter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public SoapCategoryResponse create(SoapCategoryRequest request) {
        CategoryRequest category = SoapCategoryMapper.MAPPER.toCategoryRequest(request);
        CategoryResponse categoryResponse = categoryService.create(category);

        SoapCategoryResponse soapCategoryResponse = SoapCategoryMapper.MAPPER.toCategoryResponse(request.getId(), categoryResponse);
        return soapCategoryResponse;
    }

    @Override
    public SoapCategoryResponse update(SoapCreateCategory request) {
        CategoryRequest category = SoapCategoryMapper.MAPPER.toCategoryRequestUpdate(request);

        CategoryResponse categoryResponse = categoryService.updateItem(request.getId(), category);

        SoapCategoryResponse soapCategoryResponse = SoapCategoryMapper.MAPPER.toCategoryResponse(request.getId(), categoryResponse);
        return soapCategoryResponse;
    }

    @Override
    public SoapCategoryResponse changeBase(SoapBaseCategoryRequest request) {

        CategoryResponse categoryResponse = categoryService.changeBase(request.getId(), request.getBaseCategoryId());

        SoapCategoryResponse soapCategoryResponse = SoapCategoryMapper.MAPPER.toCategoryResponse(request.getId(), categoryResponse);
        return soapCategoryResponse;
    }

    @Override
    public void remove(SoapRemoveCategoryRequest request) {
        categoryService.remove(request.getId());
    }

    @Override
    public SoapCategoryResponse getById(SoapCategoryIdRequest request) {
        CategoryResponse categoryResponse = categoryService.getById(request.getId());

        SoapCategoryResponse soapCategoryResponse = SoapCategoryMapper.MAPPER.toCategoryResponse(request.getId(), categoryResponse);
        return soapCategoryResponse;
    }

//    @Override
//    public Page<CategoryResponse> search(SoapPagedCategoryRequest request) {
//
//        Pageable pageable = PageRequest.of(request.getPageNum(),
//                request.getPageSize(), Sort.by(
//                Sort.Order.asc("id")));
//
//        CategorySearch categorySearch = SoapCategoryMapper.MAPPER.toCategorySearch(request);
//
//        Page<CategoryResponse> search = categoryService.search(categorySearch, pageable);
//
//        return null;
//    }
}
