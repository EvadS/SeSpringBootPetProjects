package com.se.example.controller;


import com.se.example.dao.model.Book;
import com.se.example.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = { "/create", "/" }, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Book book) {
        bookService.create(book);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public Mono<ResponseEntity<Book>> findById(@PathVariable("id") Integer id) {

        log.info("Get book by: {} ", id);
//        return Mono
//                .just(profile)
//                .flatMap(p -> this.profileRepository.update(id, p.getEmail()))
//                .map(p -> ResponseEntity
//                        .ok()
//                        .contentType(this.mediaType)
//                        .build());

//        return userService
//                .getUserById(id)
//                .orElseThrow(() -> new UserNotFoundException(String.format("User with id [%s] not found", id)));


        Mono<Book> e = bookService.findById(id);
       // HttpStatus status = e != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
      //  return new ResponseEntity<Mono<Book>>(e, status);

        HttpStatus status = HttpStatus.OK;
        Mono<ResponseEntity<Book>> map = e.map(r -> ResponseEntity.status(status).body(r));
        return  map;

    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public Flux<Book> findByName(@PathVariable("name") String name) {
        return bookService.findByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Book> findAll() {
        Flux<Book> emps = bookService.findAll();
        return emps;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Mono<Book> update(@RequestBody Book book) {
        return bookService.update(book);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        bookService.delete(id).subscribe();
    }

}
