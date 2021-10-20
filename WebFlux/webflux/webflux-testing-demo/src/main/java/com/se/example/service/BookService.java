package com.se.example.service;

import com.se.example.dao.model.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Skiba Evgeniy
 * @date 28.08.2021
 */
public interface BookService {
    void create(Book e);

    Mono<Book> findById(Integer id);

    Flux<Book> findByName(String name);

    Flux<Book> findAll();

    Mono<Book> update(Book e);

    Mono<Void> delete(Integer id);
}
