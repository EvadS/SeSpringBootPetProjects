package com.se.sample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.dto.FilterRequestDto;
import com.se.sample.dto.RangeFilterDto;
import com.se.sample.dto.SearchQueryDto;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ProductRepositoryImpl  implements ProductRepository {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Autowired
    ObjectMapper objectMapper;

    private static final String INDEX_NAME = "product";


    @Override
    public SearchResponse search(SearchQueryDto searchQuery) throws IOException {
        SearchRequest searchRequest = Requests.searchRequest(INDEX_NAME);

            // bool query
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("name", searchQuery.getQuery()))
                .should(QueryBuilders.matchQuery("description", searchQuery.getQuery()))
                .should(QueryBuilders.matchQuery("category", searchQuery.getQuery()));

        // facet query
        if (searchQuery.getFilter() != null) {
            FilterRequestDto filter = searchQuery.getFilter();
            if (filter.getRange() != null) {
                for (String keyToFilter : filter.getRange().keySet()) {
                    RangeFilterDto valueToFilter = filter.getRange().get(keyToFilter);

                    RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(keyToFilter);

                    if (valueToFilter.getLte() != null) {
                        rangeQueryBuilder.lte(valueToFilter.getLte());
                    }

                    if (valueToFilter.getLt() != null) {
                        rangeQueryBuilder.lt(valueToFilter.getLt());
                    }

                    if (valueToFilter.getGt() != null) {
                        rangeQueryBuilder.gt(valueToFilter.getGt());
                    }

                    if (valueToFilter.getGte() != null) {
                        rangeQueryBuilder.gte(valueToFilter.getGte());
                    }

                    boolQueryBuilder.filter(rangeQueryBuilder);
                }

            } else if (filter.getMatch() != null) {
                for (String keyToFilter : filter.getMatch().keySet()) {
                    Object valueToFilter = filter.getMatch().get(keyToFilter).toString().toLowerCase();

                    boolQueryBuilder.filter(QueryBuilders.termQuery(keyToFilter, valueToFilter));
                }
            }
        }


        // pagination
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .from(searchQuery.getPage() * searchQuery.getSize())
                .size(searchQuery.getSize())
                .query(boolQueryBuilder);

        log.info("searchSourceBuilder: {}", searchSourceBuilder);
        searchRequest.source(searchSourceBuilder);
        return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    }

    @Override
    public IndexResponse save(Product product) throws IOException {
        IndexRequest indexRequest = Requests.indexRequest(INDEX_NAME)
                .id(product.getId().toString())
                .source(convertProductToMap(product));

        RequestOptions options = RequestOptions.DEFAULT;
        return restHighLevelClient.index(indexRequest, options);
    }

    @Override
    public BulkResponse saveAll(List<Product> products) throws IOException {
        BulkRequest bulkRequest = Requests.bulkRequest();
        products.forEach(product -> {
            try {
                IndexRequest indexRequest = Requests
                        .indexRequest(INDEX_NAME)
                        .source(convertProductToMap(product));
                bulkRequest.add(indexRequest);
            } catch (JsonProcessingException e) {
                // log error
            }
        });

        RequestOptions options = RequestOptions.DEFAULT;
        return restHighLevelClient.bulk(bulkRequest, options);
    }

    private Map<String, Object> convertProductToMap(Product product) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(product);
        return objectMapper.readValue(json, Map.class);
    }

   // @PostConstruct
    private void init () throws IOException {
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);

        final var searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        log.info("---------------------------------");
        log.info("searchResponse:{}", searchResponse.getHits());
;
        log.info("---------------------------------");
        int a =10;
    }

    private  void callASynchronously(final SearchRequest searchRequest) {
        restHighLevelClient.searchAsync(searchRequest, RequestOptions.DEFAULT,
                new ActionListener<SearchResponse>() {
                    @Override
                    public void onResponse(final SearchResponse searchResponse) {

                        Arrays.stream(searchResponse.getHits().getHits()).forEach(i-> log.info(i.toString()));
                        try {
                            restHighLevelClient.close();
                        } catch (IOException e) {
                          log.error("Failed to close client {0}", e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(final Exception e) {
                        log.error( "Failed to get results {0}", e.getMessage());
                        try {
                            restHighLevelClient.close();
                        } catch (IOException ioe) {
                            log.error( "Failed to close client {0}", ioe.getMessage());
                        }
                    }
                });
    }
}
