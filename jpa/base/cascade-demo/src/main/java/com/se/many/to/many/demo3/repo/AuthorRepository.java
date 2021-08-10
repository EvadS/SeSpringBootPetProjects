package com.se.many.to.many.demo3.repo;

import com.se.many.to.many.demo3.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByName(String name);
}
