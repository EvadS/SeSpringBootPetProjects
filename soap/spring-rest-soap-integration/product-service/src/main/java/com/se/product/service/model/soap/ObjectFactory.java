//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.08.17 at 07:39:04 PM EEST 
//


package com.se.product.service.model.soap;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.se.product.service.model.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.se.product.service.model.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetProductResponse }
     * 
     */
    public GetProductResponse createGetProductResponse() {
        return new GetProductResponse();
    }

    /**
     * Create an instance of {@link Product }
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * Create an instance of {@link SoapRemoveCategoryRequest }
     */
    public SoapRemoveCategoryRequest createSoapRemoveCategoryRequest() {
        return new SoapRemoveCategoryRequest();
    }

    /**
     * Create an instance of {@link SoapPagedCategoryRequest }
     */
    public SoapPagedCategoryRequest createSoapPagedCategoryRequest() {
        return new SoapPagedCategoryRequest();
    }

    /**
     * Create an instance of {@link SoapCategoryIdRequest }
     */
    public SoapCategoryIdRequest createSoapCategoryIdRequest() {
        return new SoapCategoryIdRequest();
    }

    /**
     * Create an instance of {@link SoapCategoryResponse }
     */
    public SoapCategoryResponse createSoapCategoryResponse() {
        return new SoapCategoryResponse();
    }

    /**
     * Create an instance of {@link SoapCategory }
     * 
     */
    public SoapCategory createSoapCategory() {
        return new SoapCategory();
    }

    /**
     * Create an instance of {@link SoapCategoryRequest }
     *
     */
    public SoapCategoryRequest createSoapCategoryRequest() {
        return new SoapCategoryRequest();
    }

    /**
     * Create an instance of {@link SoapBaseCategoryRequest }
     */
    public SoapBaseCategoryRequest createSoapBaseCategoryRequest() {
        return new SoapBaseCategoryRequest();
    }

    /**
     * Create an instance of {@link GetProductRequest }
     */
    public GetProductRequest createGetProductRequest() {
        return new GetProductRequest();
    }

    /**
     * Create an instance of {@link SoapCreateCategory }
     * 
     */
    public SoapCreateCategory createSoapCreateCategory() {
        return new SoapCreateCategory();
    }

}
