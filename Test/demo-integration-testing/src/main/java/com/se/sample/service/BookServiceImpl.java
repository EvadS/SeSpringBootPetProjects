package com.se.sample.service;


import com.se.sample.model.BookRequest;
import com.se.sample.entity.Book;
import com.se.sample.exception.BookNotFoundException;
import com.se.sample.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Long createNewBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setIsbn(bookRequest.getIsbn());
        book.setAuthor(bookRequest.getAuthor());
        book.setTitle(bookRequest.getTitle());

        book = bookRepository.save(book);

        return book.getId();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        Optional<Book> requestedBook = bookRepository.findById(id);

        //!Optional.isPresent()
        if (!requestedBook.isPresent()) {
            throw new BookNotFoundException(String.format("Book with id: '%s' not found", id));
        }

        return requestedBook.get();
    }

    @Override
    @Transactional
    public Book updateBook(Long id, BookRequest bookToUpdateRequest) {

        Optional<Book> bookFromDatabase = bookRepository.findById(id);

        if (!bookFromDatabase.isPresent()) {
            throw new BookNotFoundException(String.format("Book with id: '%s' not found", id));
        }

        Book bookToUpdate = bookFromDatabase.get();

        bookToUpdate.setAuthor(bookToUpdateRequest.getAuthor());
        bookToUpdate.setIsbn(bookToUpdateRequest.getIsbn());
        bookToUpdate.setTitle(bookToUpdateRequest.getTitle());

        return bookToUpdate;
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}