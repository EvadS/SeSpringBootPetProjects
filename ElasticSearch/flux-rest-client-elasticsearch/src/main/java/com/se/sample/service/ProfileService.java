package com.se.sample.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.dao.ProfileDocument;
import com.se.sample.model.dto.FilterRequestDto;
import com.se.sample.model.dto.RangeFilterDto;
import com.se.sample.model.dto.SearchQueryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.se.sample.Constant.INDEX;

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

    public List<ProfileDocument> searchByTechnology(String technology) throws Exception {
        SearchRequest searchRequest = new SearchRequest(INDEX);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder query =  QueryBuilders.boolQuery()
                .should(new WildcardQueryBuilder("technologies.name", "*"+technology+"*"));
        searchSourceBuilder.query(query);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        return getSearchResult(searchResponse);
    }

    public SearchResponse search(SearchQueryDto searchQuery) throws IOException {
        SearchRequest searchRequest = Requests.searchRequest(INDEX);

        // bool query
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("firstName", searchQuery.getQuery()))
             //   .should(QueryBuilders.matchQuery("description", searchQuery.getQuery()))
             //   .should(QueryBuilders.matchQuery("category", searchQuery.getQuery()))
        ;

        // demo with range query
        // facet query
//        if (searchQuery.getFilter() != null) {
//            FilterRequestDto filter = searchQuery.getFilter();
//            if (filter.getRange() != null) {
//                for (String keyToFilter : filter.getRange().keySet()) {
//                    RangeFilterDto valueToFilter = filter.getRange().get(keyToFilter);
//
//                    RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(keyToFilter);
//
//                    if (valueToFilter.getLte() != null) {
//                        rangeQueryBuilder.lte(valueToFilter.getLte());
//                    }
//
//                    if (valueToFilter.getLt() != null) {
//                        rangeQueryBuilder.lt(valueToFilter.getLt());
//                    }
//
//                    if (valueToFilter.getGt() != null) {
//                        rangeQueryBuilder.gt(valueToFilter.getGt());
//                    }
//
//                    if (valueToFilter.getGte() != null) {
//                        rangeQueryBuilder.gte(valueToFilter.getGte());
//                    }
//
//                    boolQueryBuilder.filter(rangeQueryBuilder);
//                }
//
//            } else if (filter.getMatch() != null) {
//                for (String keyToFilter : filter.getMatch().keySet()) {
//                    Object valueToFilter = filter.getMatch().get(keyToFilter).toString().toLowerCase();
//
//                    boolQueryBuilder.filter(QueryBuilders.termQuery(keyToFilter, valueToFilter));
//                }
//            }
//        }

        // pagination
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .from(searchQuery.getPage() * searchQuery.getSize())
                .size(searchQuery.getSize())
                .query(boolQueryBuilder);

        log.info("searchSourceBuilder: {}", searchSourceBuilder);
        searchRequest.source(searchSourceBuilder);
        return client.search(searchRequest, RequestOptions.DEFAULT);
    }
}
