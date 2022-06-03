package com.se.sample.support;

import com.se.sample.model.Doc;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;
import org.elasticsearch.common.xcontent.XContentType;

import javax.validation.constraints.NotNull;

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



    public IndexResponse create(String index, @NotNull String documentId, Object documentObject) {
        IndexRequest indexRequest = new IndexRequest("product");
        indexRequest.id(documentId);
        indexRequest.source(documentObject);

        try {
            IndexResponse indexResponse = esClient.index(indexRequest, RequestOptions.DEFAULT);
            if (indexResponse.status() == RestStatus.ACCEPTED) {
                return indexResponse;
            }

            throw new RuntimeException("Wrong status: " + indexResponse.status());
        } catch (Exception e) {
            log.error("Error indexing: ", e);
            return null;
        }
    }




    private Mono<IndexResponse> indexDoc(Doc doc) {
        return Mono.create(sink -> {
            IndexRequest indexRequest = new IndexRequest("people", "person", doc.getUsername());
            indexRequest.source(doc.getJson(), XContentType.JSON);
            esClient.indexAsync(indexRequest, new ActionListener<IndexResponse>() {
                @Override
                public void onResponse(IndexResponse indexResponse) {
                    sink.success(indexResponse);
                }

                @Override
                public void onFailure(Exception e) {
                    sink.error(e);
                }
            });
        });
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

