package com.se.sample.support;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.RestStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.se.sample.support.Constants.MYMODEL_ES_INDEX;

@Slf4j
@Service
@RequiredArgsConstructor
public class ElasticAdapter {

    private final RestHighLevelClient esClient;




    public CreateIndexResponse createBookIndex(String  indexName) throws IOException {
//        CreateIndexRequest request = new CreateIndexRequest("users");
//        request.settings(Settings.builder()
//                .put("index.number_of_shards", 1)
//                .put("index.number_of_replicas", 2)
//        );
//        Map<String, Object> message = new HashMap<>();
//        message.put("type", "text");
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("userId", message);
//        properties.put("name", message);
//        Map<String, Object> mapping = new HashMap<>();
//        mapping.put("properties", properties);
//        request.mapping(mapping);
//

        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.settings(Settings.builder()
                .put("index.max_inner_result_window", 250)
                .put("index.write.wait_for_active_shards", 1)
                .put("index.query.default_field", "paragraph")
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2)
        );


        CreateIndexResponse indexResponse = esClient.indices().create(request, RequestOptions.DEFAULT);
        return indexResponse;
    }

    public IndexResponse create(String index, @NotNull String documentId, Object documentObject) {
        IndexRequest indexRequest = new IndexRequest(index)
                .id(documentId)
                .source(documentObject);
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
}
