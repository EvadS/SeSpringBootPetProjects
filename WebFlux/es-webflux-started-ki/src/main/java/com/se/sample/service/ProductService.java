package com.se.sample.service;

import com.se.sample.entity.dto.ProductResponse;
import com.se.sample.entity.dto.response.ProductItemResponse;
import com.se.sample.entity.dto.response.ProductRequest;
import com.se.sample.entity.model.Product;
import com.se.sample.helper.PageSupport;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<ProductItemResponse> getAll();

    Mono<ProductResponse> getById(String id);

    Mono<ProductResponse> update(String id, Product product);

    Mono save(ProductRequest productRequest);

    Mono delete(String id);

    Mono<PageSupport<ProductResponse>> getPageResponse(PageRequest of);
}
