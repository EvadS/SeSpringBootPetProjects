package com.se.product.service.service.adaptee;

import com.se.product.service.model.soap.*;

public interface CategoryAdaptee {

    SoapCategoryResponse create(SoapCategoryRequest request);

    SoapCategoryResponse update(SoapCreateCategory request);

    SoapCategoryResponse changeBase(SoapBaseCategoryRequest request);

    void remove(SoapRemoveCategoryRequest request);

    SoapCategoryResponse getById(SoapCategoryIdRequest request);

    //   Page<CategoryResponse> search(SoapPagedCategoryRequest request);
}
