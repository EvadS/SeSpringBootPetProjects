package com.se.sample.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;

import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;

import org.elasticsearch.client.RestHighLevelClient;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class ElasticSearchController {

//
//    @Autowired
//    private RestHighLevelClient client;
//
//    @GetMapping(value = "/create-index")
//    public String ping() throws IOException {
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
//      //  request.mapping(mapping);
//
//        CreateIndexResponse indexResponse = client.index(request);
//        return "created";
//    }
//
//
//
//    @GetMapping(value = "/delete-index")
//    public String deleteIndex() throws IOException {
//
//        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("users");
//
//        AcknowledgedResponse delete = client.indices().deleteIndex (deleteIndexRequest);
//        log.info("delete response:{}", delete);
//        return "deleted";
//
//    }
//
//
//    @GetMapping("/exists")
//    public Boolean checkIndexExists() throws IOException {
//
//        GetIndexRequest request = new GetIndexRequest("users");
//
//        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
//        return exists;
//    }
//
//    @GetMapping(value = "/upsert")
//    public String upsert() throws IOException {
//        IndexRequest request = new IndexRequest("users");
//        Map<String, Object> users = new HashMap<>();
//        users.put("id", "001");
//        users.put("name", "Chiwa Kantawong");
//        users.put("age", 25);
//        request.id(users.get("id").toString());
//        request.source(new ObjectMapper().writeValueAsString(users), XContentType.JSON);
//        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
//        System.out.println("response id: "+indexResponse.getId());
//
//        users = new HashMap<>();
//        users.put("id", "002");
//        users.put("name", "Pea Kantawong");
//        users.put("age", 24);
//        request.id(users.get("id").toString());
//        request.source(new ObjectMapper().writeValueAsString(users), XContentType.JSON);
//        indexResponse = client.index(request, RequestOptions.DEFAULT);
//        return "success";
//    }
//
//    //partial update
//    @GetMapping(value = "/update")
//    public UpdateResponse update() throws IOException {
//        Map<String, Object> jsonMap = new HashMap<>();
//        jsonMap.put("reason", "daily update");
//        UpdateRequest request = new UpdateRequest("users","001")
//                .doc(jsonMap);
//
//        UpdateResponse updateResponse = client.update(request,RequestOptions.DEFAULT);
//        return updateResponse;
//    }
//
//    @GetMapping(value = "get")
//    public GetResponse get() throws IOException {
//
//        GetRequest request = new GetRequest(
//                "users",
//                "001");
//
//        GetResponse getResponse = client.get(request, RequestOptions.DEFAULT);
//        return getResponse;
//    }

}
