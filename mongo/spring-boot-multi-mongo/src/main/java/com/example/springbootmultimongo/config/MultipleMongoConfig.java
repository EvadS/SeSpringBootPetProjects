/*
 * Confidential and Proprietary.
 * Do not distribute without 1-800-Flowers.com, Inc. consent.
 * Copyright 1-800-Flowers.com, Inc. 2019. All rights reserved.
 */

package com.example.springbootmultimongo.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MultipleMongoConfig {
    @Primary
    @Bean(name = "productData")
    @ConfigurationProperties(prefix = "mongodb.primary")
    public MongoProperties getProductDataConfig() {
        return new MongoProperties();
    }

    @Bean(name = "secondMongoData")
    @ConfigurationProperties(prefix = "mongodb.secondary")
    public MongoProperties getProductMetaDataConfig() {
        return new MongoProperties();
    }

    @Primary
    @Bean(name = "productDataMongoTemplate")
    public MongoTemplate productDataMongoTemplate() throws Exception {
        return new MongoTemplate(productDataFactory(getProductDataConfig()));
    }

    @Bean(name = "secondMongoTemplate")
    public MongoTemplate productMetaDataMongoTemplate() throws Exception {
        return new MongoTemplate(productDataFactory(getProductMetaDataConfig()));
    }

    @Bean
    @Primary
    public MongoDatabaseFactory productDataFactory(final MongoProperties mongo) throws Exception {
        return new SimpleMongoClientDatabaseFactory(mongo.getUri());
    }
}
