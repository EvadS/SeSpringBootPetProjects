package com.se.sample.service;

import com.se.sample.entity.dto.ProductResponse;
import com.se.sample.entity.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<Product> getAll();

    Mono<ProductResponse> getById(String id);

    Mono<ProductResponse> update(String id, Product product);

    Mono save(Product product);

    Mono delete(String id);
}
