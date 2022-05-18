package com.se.services.elasticsearch;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * This Manual test requires: Elasticsearch instance running on localhost:9200.
 *
 * The following docker command can be used: docker run -d --name es762 -p
 * 9200:9200 -e "discovery.type=single-node" elasticsearch:7.6.2
 */
@OpenAPIDefinition(info = @Info(title = "Swagger Demo", version = "1.0", description = "Documentation APIs v1.0"))
@SpringBootApplication
public class SampleSpringElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleSpringElasticsearchApplication.class, args);
    }

}
