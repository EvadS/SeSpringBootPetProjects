package com.se.product.service.endpoint;

import com.se.product.service.model.response.CategoryResponse;
import com.se.product.service.model.soap.Category;
import com.se.product.service.model.soap.GetCategoryByIdRequest;
import com.se.product.service.model.soap.GetCategoryResponse;
import com.se.product.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CategoryEndpoint {


    String a;
    private static final String NAMESPACE_URI = "http://www.service.product.se.com/model/soap";

    @Autowired
    CategoryService categoryService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCategoryByIdRequest")
    @ResponsePayload
    public GetCategoryResponse getCountry(@RequestPayload GetCategoryByIdRequest request) {
        GetCategoryResponse response = new GetCategoryResponse();

        CategoryResponse сategoryResponse = categoryService.getById(request.getId());
        //TODO se: Refactored
        Category category = new Category();

        category.setCode(сategoryResponse.getCategoryRequest().getCode());
        category.setName(сategoryResponse.getCategoryRequest().getName());
        category.setId(сategoryResponse.getId());

        response.setCategory(category);

        return response;
    }
}
