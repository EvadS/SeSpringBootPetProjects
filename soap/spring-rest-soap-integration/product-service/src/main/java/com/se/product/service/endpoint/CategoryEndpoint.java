package com.se.product.service.endpoint;


import com.se.product.service.model.soap.SoapCategory;
import com.se.product.service.model.soap.SoapCategoryRequest;
import com.se.product.service.model.soap.SoapCategoryResponse;
import com.se.product.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
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

    public CategoryEndpoint(@Qualifier("soap") CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "soapCategoryRequest")
    @ResponsePayload
    public SoapCategoryResponse getCategory(@RequestPayload SoapCategoryRequest request) {

        SoapCategory category = new SoapCategory();
        category.setId(request.getId());
        category.setCode("CODE");
        category.setName("CATEGORY NAME");

        SoapCategoryResponse response = new SoapCategoryResponse();
        response.setCategory(category);

        return response;
    }

}
