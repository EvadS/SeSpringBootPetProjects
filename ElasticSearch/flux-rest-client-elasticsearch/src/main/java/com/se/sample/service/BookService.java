package com.se.sample.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.model.Book;
import com.se.sample.model.request.BookRequest;
import com.se.sample.model.response.BookResponse;
import com.se.sample.support.ElasticAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {
    private final ObjectMapper objectMapper;
    @Value("${index.book}")
    private String bookIndex;

    private final ElasticAdapter elasticAdapter;

    public BookResponse create(BookRequest request) {

        UUID uuid = UUID.randomUUID();
        Book book = Book.builder()
                .id(uuid.toString())
                .title(request.getTitle())
                .author(request.getAuthor())
                .price(request.getPrice())
                .build();

        try {

            Map dataMap = objectMapper.convertValue(book, Map.class);
            IndexResponse indexResponse = elasticAdapter.create(bookIndex, book.getId(), dataMap);

            BookResponse bookResponse = BookResponse.builder()
                    .bookRequest(request)
                    .id(uuid.toString())
                    .build();

            return bookResponse;
        } catch (IOException ex) {
            log.error(ex.getMessage());
            throw new RuntimeException("create new book exception");
        }
    }


    public BookResponse findById(String id) throws IOException {

        GetResponse getResponse = elasticAdapter.get(bookIndex, id);
        Map<String, Object> resultMap = getResponse.getSource();

        Book book = objectMapper.convertValue(resultMap, Book.class);

        BookResponse bookResponse = BookResponse.builder()
                .build();

        return bookResponse;
    }
}