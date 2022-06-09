package com.se.sample;

import com.se.sample.dto.SearchQueryDto;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;

import java.io.IOException;
import java.util.List;

public interface ProductRepository {
    SearchResponse search(SearchQueryDto searchQueryDto) throws IOException;

    IndexResponse save(Product product) throws IOException;

    BulkResponse saveAll(List<Product> products) throws IOException;
}
