package com.se.sample.service;

import com.se.sample.model.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service

public class ProductService {

    public Mono<Product> updateItem(String productId, Product product) {
        return Mono.empty();
    }

    public Mono<Product>  getById(String productId) {
        return  Mono.empty();
    }

    public  Mono<Void> deleteItem(String id) {
        return  Mono.empty();
    }

}
