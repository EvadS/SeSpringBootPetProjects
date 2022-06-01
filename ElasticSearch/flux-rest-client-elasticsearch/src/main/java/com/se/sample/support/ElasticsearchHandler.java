package com.se.sample.support;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

@Service
@Slf4j
public class ElasticsearchHandler {


    @Value("${index.type}")
    private String type;

    private final RestHighLevelClient esClient;

    @Autowired
    public ElasticsearchHandler(RestHighLevelClient esClient) {
        this.esClient = esClient;
    }

    public Mono<Boolean> exists(String index, String documentId) {
        return Mono.create(sink -> {
            log.debug("Checking if document exists in index {}: {}", index, documentId);
            GetRequest getRequest = new GetRequest(index, type, documentId);

            esClient.existsAsync(getRequest, RequestOptions.DEFAULT, new BooleanListener(sink));
        });
    }


    @Slf4j
    @AllArgsConstructor
    static class BooleanListener implements ActionListener<Boolean> {
        private final MonoSink<Boolean> sink;

        @Override
        public void onResponse(Boolean exists) {
            log.debug("exists: {}", exists);
            sink.success(exists);
        }

        @Override
        public void onFailure(Exception e) {
            log.error("Execution failed, result {}", e.getMessage());
            sink.error(e);
        }
    }

}

