package com.se.sample.config;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.elasticsearch.client.RestClientBuilder.RequestConfigCallback;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

@Configuration
public class ElasticsearchConfig {

    @Value("${es.host}")
    private String ES_HOST;


    @Value("${es.password:#{null}}")
    private String password;

    @Value("${es.user:#{null}}")
    private String user;

    private static final String host = "127.0.0.1";
    private static final int port = 9200;

    private static final String schema = "http";
    private static final int connectTimeOut = 1000;
    private static final int socketTimeOut = 30000;
    private static final int connectionRequestTimeOut = 500;
    private static final int maxConnectNum = 100;
    private static final int maxConnectPerRoute = 100;
    private static HttpHost httpHost = null;

    static {
        httpHost = new HttpHost(host, port, schema);
    }


    @Bean
    public RestHighLevelClient esClient() {

        RestClientBuilder builder = RestClient.builder(httpHost);
        if (!StringUtils.isEmpty(user) && !StringUtils.isEmpty(password)) {
            applyAuthentication(builder, user, password);
        }

        // Async connection configuration
        builder.setRequestConfigCallback(new RequestConfigCallback() {
            @Override
            public Builder customizeRequestConfig(Builder requestConfigBuilder) {
                requestConfigBuilder.setConnectTimeout(connectTimeOut);
                requestConfigBuilder.setSocketTimeout(socketTimeOut);
                requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeOut);
                return requestConfigBuilder;
            }
        });
        // Конфигурация номера асинхронного подключения httpclient
        builder.setHttpClientConfigCallback(new HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                httpClientBuilder.setMaxConnTotal(maxConnectNum);
                httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
                return httpClientBuilder;
            }
        });

        RestHighLevelClient client = new RestHighLevelClient(builder);


        return client;
    }

    public void applyAuthentication(RestClientBuilder restClientBuilder, String user, String password) {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,new UsernamePasswordCredentials(user, password));
        restClientBuilder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            }});
    }
}
