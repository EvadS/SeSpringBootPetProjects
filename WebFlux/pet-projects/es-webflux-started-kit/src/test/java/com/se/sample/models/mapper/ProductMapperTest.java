package com.se.sample.models.mapper;

import com.se.sample.entity.Product;
import com.se.sample.models.request.ProductItemResponse;
import com.se.sample.models.response.ProductResponse;
import com.se.sample.util.ProductUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {


    ProductMapper productMapper = ProductMapper.INSTANCE;


    //TODO: the  is the problem with date time and object mapper
//    @Test
    void toProductResponseShouldWorkCorrect() throws IOException {

        List<Product> daoProducts = ProductUtil.getDaoProducts();
        Product productInput = daoProducts.get(0);

        List<ProductItemResponse> responseProductItems = ProductUtil.getResponseProductItems();
        ProductItemResponse productItemResponse = responseProductItems.get(0);

        ProductItemResponse expectedProductItemResponse = productMapper.toProductItemResponse(productInput);

        assertEquals(expectedProductItemResponse.getId(),productItemResponse.getId() );
        assertEquals(expectedProductItemResponse.getName(),productItemResponse.getName() );

    }


    void toProductShouldWorkCorrect() {
    }

    void toProductItemResponseShouldWorkCorrect() throws IOException {
//        List<Product> daoProducts = ProductUtil.getDaoProducts();
//        Product productInput = daoProducts.get(0);
//
//        List<ProductResponse> responseProductItems = ProductUtil.getResponseProductResponse();
//        ProductResponse productItemResponse = responseProductItems.get(0);
//
//
//        ProductResponse expectedProductItemResponse = productMapper.toProductResponse(productInput);
//
//        assertEquals(expectedProductItemResponse.getId(),productItemResponse.getId() );
//        assertEquals(expectedProductItemResponse.getName(),productItemResponse.getName() );
//        assertEquals(expectedProductItemResponse.getPrice(),productItemResponse.getPrice() );
//        assertEquals(expectedProductItemResponse.getCategory(),productItemResponse.getCategory() );
//        assertEquals(expectedProductItemResponse.getQuantity(),productItemResponse.getQuantity() );
//        assertEquals(expectedProductItemResponse.getDescription(),productItemResponse.getDescription() );
//        assertEquals(expectedProductItemResponse.getManufacturer(),productItemResponse.getManufacturer() );
//        assertEquals(expectedProductItemResponse.getCreatedDate(),productItemResponse.getCreatedDate() );
    }
}