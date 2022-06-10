package com.se.sample.configuration;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * RestHighLevelClient configuration
 * @author Skiba Evgeniy
 * @date 02.09.2021
 */
@Configuration
@RequiredArgsConstructor
//@EnableElasticsearchRepositories(basePackages
//        = "com.se.sample.repository")
//@ComponentScan(basePackages = { "com.se.sample"})
public class ElasticsearchConfiguration extends AbstractElasticsearchConfiguration {

    /**
     * RestHighLevelClient
     */

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration =
                ClientConfiguration
                        .builder()
                        .connectedTo("localhost:9200")
                        /*
                          .withConnectTimeout(elasticsearchProperties.getConnectTimeout())
        .withSocketTimeout(elasticsearchProperties.getSocketTimeout())
                        * */
                        .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
