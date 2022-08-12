package com.se.sample.repository;

import com.se.sample.domain.Product;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author Skiba Evgeniy
 * @date 02.09.2021
 */
public interface ProductRepository extends ElasticsearchRepository<Product, String> {

    List<Product> findAllByName(String name);

    @Query("{\"match\":{\"name\":\"?0\"}}")
    List<Product> findAllByNameUsingAnnotations(String name);
}
