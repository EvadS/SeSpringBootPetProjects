package com.example.controller;

/**
 * @author Skiba Evgeniy
 * @date 30.08.2021
 */

import com.example.domain.Book;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/books")
public class HomeController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("")
    public Flux<Book> getHome() {
        return bookRepository.findAll();
    }

    @PostMapping("")
    public Mono<Book> postBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("")
    public Mono<Book> updateBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("")
    public boolean deleteBook(@RequestBody Book book) {
        try {
            bookRepository.deleteById(book.getId()).block(); // Note this!
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}