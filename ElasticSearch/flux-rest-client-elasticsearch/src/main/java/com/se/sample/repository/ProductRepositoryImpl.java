package com.se.sample.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.model.Product;
import com.se.sample.support.ElasticAdapter;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private static final String INDEX_NAME = "product";

    private final ObjectMapper objectMapper;
    private final ElasticAdapter elasticAdapter;

    @Override
    public Mono<IndexResponse> save(Product product) throws IOException {

        Map<String, Object> stringObjectMap = convertProductToMap(product);
        return elasticAdapter.indexAsync(INDEX_NAME, String.valueOf(product.getId()), stringObjectMap);
    }


    private Map<String, Object> convertProductToMap(Product product) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(product);
        return objectMapper.readValue(json, Map.class);
    }
}
