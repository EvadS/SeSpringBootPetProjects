package com.se.sample.controller;

import com.se.sample.model.request.BookRequest;
import com.se.sample.model.response.BookResponse;
import com.se.sample.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public BookResponse create(@RequestBody BookRequest bookRequest) {
        return bookService.create(bookRequest);
    }

    @GetMapping("/{id}")
    public BookResponse findById(@PathVariable String id) throws IOException {
        return bookService.findById(id);
    }







//    @GetMapping("/all")
//    public List<Book> getBookById() throws IOException {
//        return bookDao.findAll();
//    }


//    @PutMapping("/{id}")
//    public Map<String, Object> updateBookById(@RequestBody Book book, @PathVariable String id) {
//        return bookDao.updateBookById(id, book);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteBookById(@PathVariable String id) {
//        bookDao.deleteBookById(id);
//    }


}
