package com.se.example.dao.repository;

import com.se.example.dao.model.Book;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class BookRepository {
    // TODO: implement dome test data
    public Mono<Book> findById(Integer id) {
        return Mono.empty();
    }

    public Flux<Book> findByName(final String name) {
        Book book = new Book();

        return Flux.just(book);
    }

    public Flux<Book> findAll() {

        return Flux.empty();
    }

    public Mono<Void> deleteById(Integer id) {
        return Mono.empty();
    }

    public Mono<Book> save(Book e) {
        return Mono.empty();
    }
}
