package com.se.sample.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.dao.ProfileDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.join.ScoreMode;
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
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.se.sample.util.Constant.INDEX;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ObjectMapper objectMapper;
    private final RestHighLevelClient client;


    public String createProfileDocument(ProfileDocument document) throws IOException {

        UUID uuid = UUID.randomUUID();
        document.setId(uuid.toString());

        //String in the form of JSON is not readable and looks messy
        Map<String, Object> documentMapper = objectMapper.convertValue(document, Map.class);

        IndexRequest indexRequest = new IndexRequest(INDEX)
                .id(document.getId())
                .source(documentMapper);

        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

        log.info("new index response:{}", indexResponse.getResult());
        return indexResponse.getId();
    }

    public ProfileDocument findById(String id) throws IOException {
        log.info("get by id:{}", id);
        GetRequest getRequest = new GetRequest(INDEX).id(id);

        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        Map<String, Object> resultMap = getResponse.getSource();

        return objectMapper.convertValue(resultMap, ProfileDocument.class);
    }


    public String updateProfile(ProfileDocument document) throws Exception {

        ProfileDocument resultDocument = findById(document.getId());

        UpdateRequest updateRequest = new UpdateRequest(INDEX, resultDocument.getId());

        Map<String, Object> documentMapper =
                objectMapper.convertValue(document, Map.class);

        updateRequest.doc(documentMapper);

        UpdateResponse updateResponse =
                client.update(updateRequest, RequestOptions.DEFAULT);

        return updateResponse
                .getResult()
                .name();

    }

 /*   private Mono<IndexResponse> indexDoc(Doc doc) {
        return Mono.create(sink -> {
            IndexRequest indexRequest = new IndexRequest("people", "person", doc.getUsername());
            indexRequest.source(doc.getJson(), XContentType.JSON);
            client.indexAsync(indexRequest, new ActionListener<IndexResponse>() {
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
*/
    public List<ProfileDocument> findAll() throws IOException {

        SearchRequest searchRequest = new SearchRequest(INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse =
                client.search(searchRequest, RequestOptions.DEFAULT);

        return getSearchResult(searchResponse);
    }

    private List<ProfileDocument> getSearchResult(SearchResponse response) {
        SearchHit[] searchHit = response.getHits().getHits();
        log.info("found items:{}", searchHit.length);
        List<ProfileDocument> profileDocuments =
                Arrays.stream(searchHit)
                        .map(hit -> objectMapper.convertValue(hit.getSourceAsMap(), ProfileDocument.class))
                        .collect(Collectors.toList());

        return profileDocuments;
    }

    public String deleteProfileDocument(String id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(INDEX).id(id);
        DeleteResponse response =
                client.delete(deleteRequest, RequestOptions.DEFAULT);

        return response
                .getResult()
                .name();
    }

    // TODO: bulk operation

    public List<ProfileDocument> searchByTechnology(String technology) throws Exception {

        SearchRequest searchRequest = new SearchRequest(INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();




        SearchResponse response =
                client.search(searchRequest, RequestOptions.DEFAULT);

        return getSearchResult(response);
    }

    public boolean createProductIndex() {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("product");
        createIndexRequest.settings(Settings.builder()
                .put("number_of_shards", 1)
                .put("number_of_replicas", 0)
                .put("index.requests.cache.enable", false)
                .build());
        Map<String, Map<String, String>> mappings = new HashMap<>();

        mappings.put("name", Collections.singletonMap("type", "text"));
        mappings.put("category", Collections.singletonMap("type", "keyword"));
        mappings.put("price", Collections.singletonMap("type", "long"));
        createIndexRequest.mapping(Collections.singletonMap("properties", mappings));
        try {
            CreateIndexResponse createIndexResponse = client.indices()
                    .create(createIndexRequest, RequestOptions.DEFAULT);
            return createIndexResponse.isAcknowledged();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
