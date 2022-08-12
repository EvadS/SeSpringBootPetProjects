package com.se.many.to.many.demo10.products.repo;

import com.se.many.to.many.demo10.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
