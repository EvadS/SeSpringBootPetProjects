package com.se.sample;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan("com.se.sample.properties")
public class DemoElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoElasticApplication.class, args);
    }


    //http://localhost:9200/_cat/indices?v
    @Bean
    public boolean createTestIndex(RestHighLevelClient restHighLevelClient) throws Exception {
        try {
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("product");
            restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT); // 1
        } catch (Exception ignored) {
        }

        CreateIndexRequest createIndexRequest = new CreateIndexRequest("product");
        createIndexRequest.settings(
                Settings.builder().put("index.number_of_shards", 1)
                        .put("index.number_of_replicas", 0));
        restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT); // 2

        return true;
    }

}
