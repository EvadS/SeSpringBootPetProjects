package com.se.sample.controller;

import com.se.sample.domain.Book;
import com.se.sample.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<String> getCustomers() {
        return bookService.getBooks().stream()
                .map(Book::getName)
                .collect(toList());
    }
}