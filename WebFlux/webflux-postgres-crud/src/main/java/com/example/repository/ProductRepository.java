package com.example.repository;

/**
 * @author Skiba Evgeniy
 * @date 30.08.2021
 */
import com.example.domain.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {
}
