package com.se.example.service.impl;


import com.se.example.dao.model.Book;
import com.se.example.dao.repository.BookRepository;
import com.se.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Skiba Evgeniy
 * @date 28.08.2021
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public void create(Book book) {
//        return this.profileRepository
//                .save(new Profile(null, email))
//                .doOnSuccess(profile -> this.publisher.publishEvent(new ProfileCreatedEvent(profile)));


        bookRepository.save(book).subscribe();
    }
    @Override
    public Mono<Book> findById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public Flux<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public Flux<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Mono<Book> update(Book e) {

//        return this.profileRepository
//                .findById(id)
//                .map(p -> new Profile(p.getId(), email))
//                .flatMap(this.profileRepository::save);

        return bookRepository.save(e);
    }

    @Override
    public Mono<Void> delete(Integer id) {
//        return this.profileRepository
//                .findById(id)
//                .flatMap(p -> this.profileRepository.deleteById(p.getId()).thenReturn(p));

        return bookRepository.deleteById(id);
    }
}
