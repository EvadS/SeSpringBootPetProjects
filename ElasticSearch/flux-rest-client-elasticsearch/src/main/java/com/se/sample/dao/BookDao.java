package com.se.sample.dao;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.model.Book;
import com.se.sample.support.ElasticAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookDao {

    private final ObjectMapper objectMapper;
    private final ElasticAdapter elasticAdapter;
    @Value("${index.book}")
    private String bookIndex;

    public Book createBook(Book book) throws JsonProcessingException {
        UUID uuid = UUID.randomUUID();
        book.setId(uuid.toString());
        Map<String, Object> documentMapper = objectMapper.convertValue(book, Map.class);
        IndexRequest request = new IndexRequest(bookIndex);
        request.id(book.getId());
        request.source(documentMapper, XContentType.JSON);

        try {
            // IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            IndexResponse indexResponse = elasticAdapter.create(bookIndex, book.getId(), documentMapper);
            log.info("insert response: {}", indexResponse);
        } catch (ElasticsearchException e) {
            e.printStackTrace();
            log.error(e.getDetailedMessage());
        } catch (java.io.IOException ex) {
            ex.getLocalizedMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    public Book insertBook(Book book) throws JsonProcessingException {
        book.setId(UUID.randomUUID().toString());
        Map<String, Object> documentMapper = objectMapper.convertValue(book, Map.class);

        try {
            IndexResponse indexResponse = elasticAdapter.create(bookIndex, book.getId(), documentMapper);
            log.info("insert response: {}", indexResponse);
        } catch (ElasticsearchException e) {
            e.printStackTrace();
            log.error(e.getDetailedMessage());
        } catch (java.io.IOException ex) {
            ex.getLocalizedMessage();
        }
        return book;
    }

    public Book getBookById(String id) {
        GetRequest getRequest = new GetRequest(bookIndex, id);
        GetResponse getResponse = null;
        try {
            getResponse = elasticAdapter.get(bookIndex, id);
            int a = 0;
        } catch (java.io.IOException e) {
            log.error(e.getLocalizedMessage());
        }
        Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();

        return objectMapper
                .convertValue(sourceAsMap, Book.class);

    }

    public Map<String, Object> updateBookById(String id, Book book) {
        Map<String, Object> error = new HashMap<>();
        error.put("Error", "Unable to update book");
        try {
            String bookJson = objectMapper.writeValueAsString(book);

            UpdateResponse updateResponse = elasticAdapter.update(bookIndex, id, bookJson, true);
            Map<String, Object> sourceAsMap = updateResponse.getGetResult().sourceAsMap();

            log.info("update response: {}", updateResponse);
            return sourceAsMap;
        } catch (JsonProcessingException e) {
            e.getMessage();
        } catch (java.io.IOException e) {
            e.getLocalizedMessage();
        }
        return error;
    }

    public void deleteBookById(String id) {
        try {
            DeleteResponse deleteResponse = elasticAdapter.delete(bookIndex, id);
            log.info("delete response: {}", deleteResponse);
        } catch (java.io.IOException e) {
            e.getLocalizedMessage();
        }
    }
}
