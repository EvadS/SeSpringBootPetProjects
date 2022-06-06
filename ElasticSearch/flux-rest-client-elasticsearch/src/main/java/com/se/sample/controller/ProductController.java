package com.se.sample.controller;

import com.se.sample.model.Product;
import com.se.sample.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.IOException;


@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @PostMapping
    public Mono<IndexResponse> addProduct(@RequestBody Product product) throws IOException {
        return productRepository.save(product);
    }
}
