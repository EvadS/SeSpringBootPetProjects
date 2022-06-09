package com.se.sample.service;

/**
 * @author Skiba Evgeniy
 * @date 02.09.2021
 */

import java.util.List;
import java.util.Map;

import com.se.sample.domain.Product;
import org.elasticsearch.search.aggregations.Aggregation;

public interface HighLevelClientProductService {

    Product getProduct(String id);

    Map<String, Long> aggregateTerm(String term);

    Product createProduct(Product product);

    boolean deleteProduct(String id);

    boolean createProductIndex();
}