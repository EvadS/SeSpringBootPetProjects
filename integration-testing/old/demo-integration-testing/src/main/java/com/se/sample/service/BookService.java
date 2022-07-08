package com.se.sample.service;

import com.se.sample.model.BookRequest;
import com.se.sample.entity.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookService {
    Long createNewBook(BookRequest bookRequest);

    List<Book> getAllBooks();

    Book getBookById(Long id);

    @Transactional
    Book updateBook(Long id, BookRequest bookToUpdateRequest);

    void deleteBookById(Long id);
}
