package com.se.sample.service;

import com.se.sample.exception.ResourceNotFoundException;
import com.se.sample.model.BookEntity;
import com.se.sample.model.request.BookRequest;
import com.se.sample.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RestService {
    private final BookRepository bookRepository;

    @Autowired
    public RestService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String getBookStats(Long id) {

        BookEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        String result = "{ID : " + book.getId().toString() + ",Title : " + book.getTitle() + ",Author :" + book.getAuthor() + " }";

        return result;
    }

    public String createBook(BookRequest bookRequest) {
        BookEntity book = new BookEntity(bookRequest.getTitle(), bookRequest.getAuthor());
        bookRepository.save(book);

        return book.toString();
    }
}