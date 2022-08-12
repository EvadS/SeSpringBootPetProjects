package com.se.services.elasticsearch.dao;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.services.elasticsearch.document.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductDao {

    public static final String PRODUCT_INDEX = "product";

    private final RestHighLevelClient restHighLevelClient;
    private final ObjectMapper objectMapper;

    public Product createProduct(Product product) throws JsonProcessingException {
        IndexRequest indexRequest = new IndexRequest(PRODUCT_INDEX);
        indexRequest.id(product.getId());
        indexRequest.type("_doc");
        //indexRequest.source(product, XContentType.JSON);

        indexRequest.source(new ObjectMapper().writeValueAsString(product), XContentType.JSON);

        try {
            IndexResponse indexResponse = restHighLevelClient.index(indexRequest,
                    RequestOptions.DEFAULT);
            if (indexResponse.status() == RestStatus.ACCEPTED) {
                return product;
            } else if (indexResponse.status() == RestStatus.OK) {
                return product;
            }

            throw new RuntimeException("Wrong status: " + indexResponse.status());
        } catch (Exception e) {
            log.error("Error indexing, product: {}", product, e);
            return null;
        }
    }


  //  @PostConstruct
//    private void init() throws IOException {
//        SearchResponse search = search("");
//        List<Product> products = new ArrayList<>();
//
//        SearchHit[] hits = search.getHits().getHits();
//
//        for (SearchHit hit : hits) {
//
//            log.info("hit as map   :{}", hit.getSourceAsMap());
//            log.info("hit as string:{}", hit.getSourceAsString());
//
//            final Product product =
//                    objectMapper.readValue(hit.getSourceAsString(), Product.class);
//            product.setId(hit.getId());
//            log.info("Product: {}", product);
//            products.add(product);
//        }
//
//    }

    // TODO: move to stream
    public List<Product> searchProduct(String field, String value){
        List<Product> productsList = new ArrayList<>();
        SearchResponse search = search(field, value);

        for (final SearchHit hit : search.getHits().getHits()) {
            final String doc = hit.getSourceAsString();
            try {
                Product product = JSON.parseObject(doc, Product.class);
                productsList.add(product);
                log.info("Product:{}", product);
            } catch (JSONException ex) {
                log.error("failed load data {}, error {}", doc, ex);
                continue;
            }
        }

        return productsList;
    }

    public SearchResponse search(String field, String value)  {
       //QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "test5");
       QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(field, value);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(matchQueryBuilder);

        SearchRequest searchRequest = new SearchRequest(PRODUCT_INDEX);
        if (StringUtils.hasLength(value)) {
            searchRequest.source(sourceBuilder);
        }

        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        } catch (IOException e) {
            log.error("Search error: {}", e.getMessage());
            e.printStackTrace();
        }
        return searchResponse;
    }

    public Product updateProduct(String id, Product product) throws IOException {

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(PRODUCT_INDEX);
        // updateRequest.type("_doc");
        updateRequest.id(id);

        String bookJson = objectMapper.writeValueAsString(product);
        updateRequest.doc(bookJson, XContentType.JSON);

        try {
            UpdateResponse update = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
            if (update.status() == RestStatus.ACCEPTED) {
                return product;
            }

            throw new RuntimeException("Wrong status: " + update.status());
        } catch (Exception e) {
            log.error("Error indexing, product: {}", product, e);
            return null;
        }
    }

    public List<Product> bulkInsert(List<Product> products) {
        BulkRequest bulkRequest = new BulkRequest();
        products
                .forEach(product -> {
                    IndexRequest indexRequest = new IndexRequest("product");
                    indexRequest.id(product.getId());
                    bulkRequest.add(indexRequest);
                });

        try {
            BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            if (!bulk.hasFailures()) {
                return products;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public Product getProduct(String id) {
        SearchRequest searchRequest = new SearchRequest("product");
        SearchSourceBuilder sourceBuilder = SearchSourceBuilder.searchSource();
        IdsQueryBuilder idsQueryBuilder = QueryBuilders.idsQuery().addIds(id);
        sourceBuilder.query(idsQueryBuilder);
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            return toProductList(response.getHits().getHits()).stream().findFirst().orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public boolean deleteProduct(String id) {
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.id(id);
        try {
            DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            if (deleteResponse.status() == RestStatus.ACCEPTED) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private List<Product> toProductList(SearchHit[] searchHits) throws Exception {
        List<Product> productList = new ArrayList<>();
        for (SearchHit searchHit : searchHits) {
            productList.add(objectMapper.readValue(searchHit.getSourceAsString(), Product.class));
        }
        return productList;
    }

    public Map<String, Long> aggregateTerm(String term) {
        SearchRequest searchRequest = new SearchRequest("product");
        SearchSourceBuilder sourceBuilder = SearchSourceBuilder.searchSource();
        TermsAggregationBuilder terms = AggregationBuilders.terms(term).field(term);

        sourceBuilder.size(0);
        sourceBuilder.aggregation(terms);

        searchRequest.source(sourceBuilder);
        try {
            Map<String, Long> result = new HashMap<>();
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            List<Aggregation> aggregations = response.getAggregations().asList();
            aggregations
                    .forEach(aggregation -> {
                        ((Terms) aggregation).getBuckets()
                                .forEach(bucket -> result.put(bucket.getKeyAsString(), bucket.getDocCount()));
                    });
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public Page<Product> fetch(PageRequest pageable, String filter, String sort) {
        log.trace("fetch,filter={}", filter);

        if (org.apache.commons.lang3.StringUtils.isEmpty(filter)){
            return findAll( pageable,  filter);
        }
        else{
           return findAllByFilter(pageable, filter);
        }
    }

    private Page<Product> findAll(PageRequest pageable, String filter) {
        // TODO:
        return Page.empty();
    }

    private Page<Product> findAllByFilter(PageRequest pageable, String filter) {
        // TODO:
        return Page.empty();
    }
}

