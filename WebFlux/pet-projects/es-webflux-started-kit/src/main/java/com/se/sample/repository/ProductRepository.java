package com.se.sample.repository;



import com.se.sample.entity.Product;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveElasticsearchRepository<Product, String> {
    Mono<Boolean> existsByName(String name);
}