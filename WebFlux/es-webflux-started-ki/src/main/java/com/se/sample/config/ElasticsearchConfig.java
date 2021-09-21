package com.se.sample.config;


import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.reactive.function.client.ExchangeStrategies;

import javax.annotation.PostConstruct;



/**
 * ES client configuration class
 * Configuring IP and ports in the abstract class AbstractElasticsearchConfiguration, overwriting methods
 * @author
 */

@Profile("!test")
@Configuration
@ComponentScan(basePackages = {"com.se.sample.config"})
@EnableElasticsearchRepositories(basePackages = "com.se.sample.repository")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {

    @Value("${spring.data.elasticsearch.client.reactive.endpoints}")
    private String elassandraHostAndPort;

    @Bean(name = { "elasticsearchOperations", "elasticsearchTemplate" })
    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(elassandraHostAndPort)
                .withWebClientConfigurer(webClient -> {
                    ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                            .codecs(configurer -> configurer.defaultCodecs()
                                    .maxInMemorySize(-1))
                            .build();
                    return webClient.mutate().exchangeStrategies(exchangeStrategies).build();
                })
                .build();

        return RestClients
                .create(clientConfiguration)
                .rest();
    }

    @PostConstruct
    private void init(){
        int a =0;
    }



}
