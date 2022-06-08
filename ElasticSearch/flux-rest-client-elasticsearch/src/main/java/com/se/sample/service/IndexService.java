package com.se.sample.service;

import com.se.sample.support.ElasticAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class IndexService {

    private final ElasticAdapter elasticAdapter;

    public Boolean indexExists(String indexName) {
        return elasticAdapter.exists(indexName);
    }

    public Mono<Boolean> indexExistsAsync(String indexName) {
        return elasticAdapter.existsAsync(indexName);
    }

    public Boolean createIndex(String indexName) {
        return elasticAdapter.createIndex(indexName);
    }

    public Mono<Boolean> createIndexAsync(String indexName) {
        return elasticAdapter.createIndexAsync(indexName)
                .map(i -> i.isAcknowledged());
    }

    public String[] getAllIndexes() {
        try {
            return  elasticAdapter.getAllIndexes();
        } catch (IOException e) {
            log.error(e.getMessage());
            return new String[]{};
        }
    }

    public Mono<String[]> getAllIndexesAsync() {
       return elasticAdapter.getAllIndexesAsync()
               .onErrorReturn(new String[]{});
    }

    public Boolean dropIndex(String indexName) {
        return elasticAdapter.deleteIndex(indexName);
    }

    public Mono<Boolean> dropIndexAsync(String indexName) {
       return elasticAdapter.deleteIndexAsync(indexName);
    }
}
