package com.se.sample.service;

import com.se.sample.domain.Product;

import java.util.List;
import java.util.Optional;

/**
 * @author Skiba Evgeniy
 * @date 02.09.2021
 */
public interface SpringDataProductService {

    Product createProduct(Product product);

    Optional<Product> getProduct(String id);

    void deleteProduct(String id);

    Iterable<Product> insertBulk(List<Product> products);

    List<Product> getProductsByName(String name);

    List<Product> getProductsByNameUsingAnnotation(String name);
}
