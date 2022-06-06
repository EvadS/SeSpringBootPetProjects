package com.se.sample.support;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ElasticAdapter {

    private final RestHighLevelClient esClient;


}
