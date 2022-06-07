package com.se.sample.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.model.Product;
import com.se.sample.support.ElasticAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    @Value("${index.product}")
    private String INDEX_NAME;

    private final ObjectMapper objectMapper;
    private final ElasticAdapter elasticAdapter;

    @Override
    public Mono<IndexResponse> create(Product product) throws IOException {
        Map<String, Object> stringObjectMap = convertProductToMap(product);
        return elasticAdapter.indexAsync(INDEX_NAME, String.valueOf(product.getId()), stringObjectMap);

    }

    private Map<String, Object> convertProductToMap(Product product) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(product);
        return objectMapper.readValue(json, Map.class);
    }

    @Override
    public Mono<IndexResponse> update(String id, Product product) {
        return null;
    }

    @Override
    public Mono<IndexResponse> delete(String id) {
        return null;
    }

    @Override
    public Mono<IndexResponse> getById(String id) {
        return null;
    }
}
