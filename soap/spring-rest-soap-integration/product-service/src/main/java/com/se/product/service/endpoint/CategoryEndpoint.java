package com.se.product.service.endpoint;



import com.se.product.service.model.soap.SoapCategoryRequest;
import com.se.product.service.model.soap.SoapCategoryResponse;
import com.se.product.service.model.soap.SoapCreateCategory;
import com.se.product.service.service.adaptee.CategoryAdaptee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final CategoryAdaptee categoryAdaptee;

    public CategoryEndpoint(CategoryAdaptee categoryAdaptee) {
        this.categoryAdaptee = categoryAdaptee;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "soapCategoryRequest")
    @ResponsePayload
    public SoapCategoryResponse getCategory(@RequestPayload SoapCategoryRequest request) {
        logger.info("Get category request, id:{}", request.getBaseCategoryId());
        return categoryAdaptee.create(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "soapCreateCategory")
    @ResponsePayload
    public SoapCategoryResponse createCategory(@RequestPayload SoapCreateCategory request) {

        logger.info("Create category request, id:{}", request.getBaseCategoryId());
        return categoryAdaptee.update(request);
    }
}
