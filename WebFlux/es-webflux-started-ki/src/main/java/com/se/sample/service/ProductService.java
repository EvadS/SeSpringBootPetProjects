package com.se.sample.service;

import com.se.sample.entity.Product;
import com.se.sample.models.filter.ESSearchFilter;
import com.se.sample.models.response.ProductResponse;
import com.se.sample.models.request.ProductItemResponse;
import com.se.sample.models.request.ProductRequest;

import com.se.sample.helper.PageSupport;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<ProductItemResponse> getAll();

    Mono<ProductResponse> getById(String id);

    Mono<ProductResponse> update(String id, ProductRequest product);

    Mono save(ProductRequest productRequest);

    Mono delete(String id);

    Mono<PageSupport<ProductResponse>> getPageResponse(PageRequest of);

    Page<ProductResponse> findAll(org.springframework.data.domain.Pageable pageable);

}
