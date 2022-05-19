package com.se.services.elasticsearch.util;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class ElasticUtils {
    static public List<Sort.Order> getSortOrders(String sort) {
        //consider first sort element, multi-column sorting is the future
        String[] sortEntry = sort.split(",")[0].split(":");
        String sortField = sortEntry[0];
        String sortDirection = sortEntry[1];

        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order("DESC".toLowerCase().equals(sortDirection.toLowerCase()) ? Sort.Direction.DESC : Sort.Direction.ASC, sortField));
        return orders;
    }
}


