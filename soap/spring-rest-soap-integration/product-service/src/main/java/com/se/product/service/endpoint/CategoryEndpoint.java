package com.se.product.service.endpoint;


import com.se.product.service.model.soap.GetCategoryByIdRequest;
import com.se.product.service.model.soap.GetCategoryResponse;
import com.se.product.service.service.CategoryService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * register the class with Spring WS as a Web Service Endpoint
 */
@Endpoint
public class CategoryEndpoint {

    private static final String NAMESPACE_URI = "http://www.service.product.se.com/model/soap";


    private final CategoryService categoryService;
    

    public CategoryEndpoint(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCategoryByIdRequest")
    @ResponsePayload
    public GetCategoryResponse getCategory(@RequestPayload GetCategoryByIdRequest request) {

        com.se.product.service.model.soap.Category category = new com.se.product.service.model.soap.Category();
        category.setCode("CODE");
        category.setName("CATEGORY NAME");

        GetCategoryResponse response = new GetCategoryResponse();
        response.setCategory(category);

        return response;
    }

}
