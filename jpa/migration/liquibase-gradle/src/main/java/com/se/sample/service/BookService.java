package com.se.sample.service;

import com.se.sample.domain.Book;
import com.se.sample.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;



@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    @PostConstruct
    public void printBookSize() {
        long count = bookRepository.count();
        log.info("Book table contains {} entries", count);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}
