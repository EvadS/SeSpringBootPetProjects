package com.se.sample.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.dao.ProfileDocument;
import com.se.sample.model.dto.SearchQueryDto;
import com.se.sample.support.ElasticAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
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
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
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
    private final ElasticAdapter elasticAdapter;

    public String createProfileDocument(ProfileDocument document) throws IOException {

        UUID uuid = UUID.randomUUID();
        document.setId(uuid.toString());

        //String in the form of JSON is not readable and looks messy
        Map<String, Object> documentMapper = objectMapper.convertValue(document, Map.class);

        IndexResponse indexResponse = elasticAdapter.create(INDEX, uuid.toString(), documentMapper);

        log.info("new index response:{}", indexResponse.getResult());
        return indexResponse.getId();
    }

    public ProfileDocument findById(String id) throws IOException {
        log.info("get by id:{}", id);

        GetResponse getResponse = elasticAdapter.get(INDEX, id);
        Map<String, Object> resultMap = getResponse.getSource();

        if(resultMap== null || resultMap.isEmpty()){
            throw  new RuntimeException("Not found");
        }

        return objectMapper.convertValue(resultMap, ProfileDocument.class);
    }

    public String updateProfile(String id, ProfileDocument document) throws Exception {
        Map<String, Object> documentMapper = objectMapper.convertValue(document, Map.class);

        UpdateResponse updateResponse = elasticAdapter.update(INDEX ,id ,documentMapper, false);

        return updateResponse
                .getResult()
                .name();
    }

    public List<ProfileDocument> findAll() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        SearchResponse searchResponse = elasticAdapter.search(searchSourceBuilder, INDEX);

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

        DeleteResponse response = elasticAdapter.delete(INDEX, id);
        return response
                .getResult()
                .name();
    }

    public List<ProfileDocument> searchByTechnology(String technology) throws Exception {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder query = QueryBuilders.boolQuery()
                .should(new WildcardQueryBuilder("technologies.name", "*" + technology + "*"));
        searchSourceBuilder.query(query);

        SearchResponse search = elasticAdapter.search(searchSourceBuilder, INDEX);
        return getSearchResult(search);
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
        return elasticAdapter.search(searchSourceBuilder, INDEX);
    }
}
