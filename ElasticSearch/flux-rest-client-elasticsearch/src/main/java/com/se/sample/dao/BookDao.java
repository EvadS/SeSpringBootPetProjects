package com.se.sample.dao;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.model.Book;
import com.se.sample.support.ElasticAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookDao {

    private final String INDEX = "bookdata2";

    private final RestHighLevelClient restHighLevelClient;
    private final ObjectMapper objectMapper;

    private final ElasticAdapter elasticAdapter;


    public Book createBook(Book book) throws JsonProcessingException {
        UUID uuid = UUID.randomUUID();
        book.setId(uuid.toString());
        Map<String, Object> documentMapper = objectMapper.convertValue(book, Map.class);
        IndexRequest request = new IndexRequest(INDEX);
        request.id(book.getId());
        request.source(documentMapper, XContentType.JSON);

        try {
           // IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            IndexResponse  indexResponse = elasticAdapter.create(INDEX, book.getId(), documentMapper);
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
       // String jsonString = objectMapper.writeValueAsString(book);

        Map<String, Object> documentMapper = objectMapper.convertValue(book, Map.class);

        try {
            IndexResponse  indexResponse = elasticAdapter.create(INDEX, book.getId(), documentMapper);
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
        GetRequest getRequest = new GetRequest(INDEX, id);
        GetResponse getResponse = null;
        try {
             getResponse = elasticAdapter.get(INDEX, id);
             int a =0;
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

            UpdateResponse updateResponse = elasticAdapter.update(INDEX, id, bookJson,true);
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
            DeleteResponse deleteResponse = elasticAdapter.delete(INDEX, id);
            log.info("delete response: {}", deleteResponse);
        } catch (java.io.IOException e) {
            e.getLocalizedMessage();
        }
    }




@PostConstruct
private void init() throws IOException {
    SearchResponse searchResponse = search1();
        int a =0;
    }


    public SearchResponse search1() throws IOException {
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "Java Always20");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(matchQueryBuilder);

        SearchRequest searchRequest = new SearchRequest(INDEX);
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse =
                restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        return searchResponse;
    }


}
