package com.se.sample.dao;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.model.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookDao {

    private final String INDEX = "bookdata2";
    private final String TYPE = "_doc";

    private final RestHighLevelClient restHighLevelClient;
    private final ObjectMapper objectMapper;


    void create() throws IOException {
        // 1. Создаем индексный запрос
        CreateIndexRequest request = new CreateIndexRequest("Название индекса");
        // 2. Клиент выполняет запрос indicatorClient и получает ответ после запроса.
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
    }
        public Book insertBook(Book book) throws JsonProcessingException {
        book.setId(UUID.randomUUID().toString());

        String jsonString = objectMapper.writeValueAsString(book);

            IndexRequest request = new IndexRequest(INDEX);
            request.id(book.getId());
            request.source(jsonString, XContentType.JSON);

        try {
            IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            log.info("insert response: {}", response);
        } catch(ElasticsearchException e) {
            e.printStackTrace();
            log.error(e.getDetailedMessage());
        } catch (java.io.IOException ex){
            ex.getLocalizedMessage();
        }catch (Exception e){
            e.printStackTrace();
        }
        return book;
    }

    public Map<String, Object> getBookById(String id){
        GetRequest getRequest = new GetRequest(INDEX, id);
        GetResponse getResponse = null;
        try {
            getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        } catch (java.io.IOException e){
            log.error(e.getLocalizedMessage());

        }
        Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
        return sourceAsMap;
    }

    public Map<String, Object> updateBookById(String id, Book book){
        UpdateRequest updateRequest = new UpdateRequest(INDEX,  id)
                .fetchSource(true);    // Fetch Object after its update
        Map<String, Object> error = new HashMap<>();
        error.put("Error", "Unable to update book");
        try {
            String bookJson = objectMapper.writeValueAsString(book);
            updateRequest.doc(bookJson, XContentType.JSON);
            UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
            Map<String, Object> sourceAsMap = updateResponse.getGetResult().sourceAsMap();

            log.info("update response: {}", updateResponse);
            return sourceAsMap;
        }catch (JsonProcessingException e){
            e.getMessage();
        } catch (java.io.IOException e){
            e.getLocalizedMessage();
        }
        return error;
    }

    public void deleteBookById(String id) {
        DeleteRequest deleteRequest = new DeleteRequest(INDEX,  id);
        try {
            DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            log.info("delete response: {}",deleteResponse);
        } catch (java.io.IOException e){
            e.getLocalizedMessage();
        }
    }

}
