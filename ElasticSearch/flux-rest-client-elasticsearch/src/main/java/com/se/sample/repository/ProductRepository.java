package com.se.sample.repository;

import com.se.sample.model.Product;
import org.elasticsearch.action.index.IndexResponse;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface ProductRepository {
    Mono<IndexResponse> create(Product product) throws IOException;
    Mono<IndexResponse> update(String id, Product product) ;
    Mono<IndexResponse> delete(String id);
    Mono<IndexResponse> getById(String id);
}
