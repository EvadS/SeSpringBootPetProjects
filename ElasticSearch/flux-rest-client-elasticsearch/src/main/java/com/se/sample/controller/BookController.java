package com.se.sample.controller;


import com.se.sample.dao.BookDao;
import com.se.sample.model.Book;
import org.elasticsearch.action.ActionListener;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.MonoSink;

import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @PostMapping
    public Book insertBook(@RequestBody Book book) throws Exception{
        return bookDao.insertBook(book);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable String id){
        return bookDao.getBookById(id);
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateBookById(@RequestBody Book book, @PathVariable String id){
        return bookDao.updateBookById(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable String id){
         bookDao.deleteBookById(id);
    }


}
