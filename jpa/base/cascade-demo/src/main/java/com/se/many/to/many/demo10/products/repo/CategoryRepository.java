package com.se.many.to.many.demo10.products.repo;

import com.se.many.to.many.demo10.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
