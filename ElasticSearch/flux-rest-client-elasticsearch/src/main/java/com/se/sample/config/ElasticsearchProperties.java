package com.se.sample.config;

import lombok.Data;
import org.apache.http.HttpHost;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticsearchProperties {
    private List<String> hosts;
    private int connectTimeout;
    private int connectionRequestTimeout;
    private int socketTimeout;
    private int maxRetryTimeoutMillis;

    private String password;
    private String user;

    private String indexBook;
    private String indexProduct;


    HttpHost[] hosts() {
        return hosts
                .stream()
                .map(HttpHost::create)
                .toArray(HttpHost[]::new);
    }
}
