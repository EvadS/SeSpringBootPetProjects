package com.se.sample.repository;

import com.se.sample.model.Product;
import org.elasticsearch.action.index.IndexResponse;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface ProductRepository {
    Mono<IndexResponse> save(Product product) throws IOException;
}
