package com.se.sample.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.se.sample.entity.Product;
import com.se.sample.models.request.ProductItemResponse;
import com.se.sample.models.response.ProductResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;


import java.io.File;
import java.io.IOException;
import java.util.List;

public class ProductUtil {


    private static final String PRODUCT_DAO_PATH = "mock/product/dao-products.json";
    private static final String RESPONSE_PRODUCT_ITEM_PATH = "mock/product/response-product-item.json";
    private static final String RESPONSE_PRODUCT_FULL_PATH  = "mock/product/response-product.json";

    public static List<Product> getDaoProducts() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Resource resource = new ClassPathResource(PRODUCT_DAO_PATH);


        File file = resource.getFile();

        return objectMapper.readValue(file, new TypeReference<List<Product>>() {
        });
    }

    public static List<ProductItemResponse> getResponseProductItems() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        Resource resource = new ClassPathResource(RESPONSE_PRODUCT_ITEM_PATH);

        File file = resource.getFile();

        return objectMapper.readValue(file, new TypeReference<List<ProductItemResponse>>() {
        });
    }

    public static List<ProductResponse> getResponseProductResponse() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        Resource resource = new ClassPathResource(RESPONSE_PRODUCT_FULL_PATH);

        File file = resource.getFile();

        return objectMapper.readValue(file, new TypeReference<List<ProductResponse>>() {
        });
    }
}
