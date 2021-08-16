package com.se.product.service.endpoint;


import com.se.product.service.domain.Category;
import com.se.product.service.model.request.CategoryRequest;
import com.se.product.service.model.response.CategoryResponse;
import com.se.product.service.model.soap.SoapCategory;
import com.se.product.service.model.soap.SoapCategoryRequest;
import com.se.product.service.model.soap.SoapCategoryResponse;
import com.se.product.service.model.soap.SoapCreateCategory;
import com.se.product.service.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(CategoryEndpoint.class);
    private static final String NAMESPACE_URI = "http://www.service.product.se.com/model/soap";

    private final CategoryService categoryService;

    public CategoryEndpoint(@Qualifier("rest") CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "soapCategoryRequest")
    @ResponsePayload
    public SoapCategoryResponse getCategory(@RequestPayload SoapCategoryRequest request) {

        logger.info("Get category request, id:{}", request.getBaseCategoryId());
        SoapCategory category = new SoapCategory();
        category.setId(request.getId());
        category.setCode("CODE");
        category.setName("CATEGORY NAME");


        SoapCategoryResponse response = new SoapCategoryResponse();
        response.setCategory(category);

        return response;
    }

    // TODO:
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "soapCreateCategory")
    @ResponsePayload
    public SoapCategoryResponse createCategory(@RequestPayload SoapCreateCategory request) {
        logger.info("Create category request, id:{}", request.getBaseCategoryId());

        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setBaseCategory(request.getBaseCategoryId());
        categoryRequest.setCode(request.getCode());
        categoryRequest.setName(request.getName());

        CategoryResponse response = categoryService.create(categoryRequest);

        SoapCategoryResponse soapResponse = new SoapCategoryResponse();
        SoapCategory soapCategory = new SoapCategory();

        soapCategory.setId(response.getId());
        soapCategory.setCode(response.getCategoryRequest().getCode());
        soapCategory.setName(response.getCategoryRequest().getName());

        soapResponse.setCategory(soapCategory);

        logger.info("Return soap response");

        return soapResponse;
    }
}
