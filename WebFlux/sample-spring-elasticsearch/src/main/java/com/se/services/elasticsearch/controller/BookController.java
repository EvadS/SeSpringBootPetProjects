package com.se.services.elasticsearch.controller;

import com.se.services.elasticsearch.dao.BookDao;
import com.se.services.elasticsearch.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookDao bookDao;

    @GetMapping("/{id}")
    public Map<String, Object> getBookById(@PathVariable String id) {
        return bookDao.getBookById(id);
    }

    @GetMapping("/all")
    public List<Book> getBookById() throws IOException {
        return bookDao.findAll();
    }

    @PostMapping
    public Book insertBook(@RequestBody Book book) {
        return bookDao.insertBook(book);
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateBookById(@RequestBody Book book, @PathVariable String id) {
        return bookDao.updateBookById(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable String id) {
        bookDao.deleteBookById(id);
    }
}
