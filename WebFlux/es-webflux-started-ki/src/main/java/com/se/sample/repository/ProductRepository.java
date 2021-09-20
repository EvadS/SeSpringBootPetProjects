package com.se.sample.repository;


import com.se.sample.entity.model.Product;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveElasticsearchRepository<Product, String> {
}