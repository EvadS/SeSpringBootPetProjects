package com.se.many.to.many.demo3.repo;

import com.se.many.to.many.demo3.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String name);
}