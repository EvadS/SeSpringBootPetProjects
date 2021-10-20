package com.example.repository;

import com.example.domain.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author Skiba Evgeniy
 * @date 30.08.2021
 */

public interface BookRepository extends ReactiveCrudRepository<Book, Long> {

}
