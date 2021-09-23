package com.se.sample;

import com.se.sample.config.ElasticsearchConfig;
import com.se.sample.entity.Product;
import com.se.sample.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.se.sample.entity.Product.PRODUCT_INDEX;

@Slf4j
@SpringBootApplication
@EnableReactiveElasticsearchRepositories
@ConfigurationPropertiesScan(basePackageClasses = {ElasticsearchConfig.class})
public class EsWebfluxStartedKitApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsWebfluxStartedKitApplication.class, args);
    }

//
//    @Bean
//    @ConditionalOnProperty("initial-import.enabled")
//    public SampleDataSet dataSet() {
//        return new SampleDataSet();
//    }

    @Autowired
private ElasticsearchOperations elasticsearchOperations;

    @Bean
    CommandLineRunner run(UserRepository personRepository) {
        return args -> {
            int a =0;

            getProductByName();
            log.info("Application started");
        };
    }

    public  void getProductByName() {

        String productName ="product name 2";
        Query searchQuery = new StringQuery(
                "{\"match\":{\"name\":{\"query\":\""+ productName + "\"}}}\"");

        SearchHits<Product> productHits = elasticsearchOperations.search(
                searchQuery,
                Product.class,
                IndexCoordinates.of(PRODUCT_INDEX));

        List<Product> productMatches = new ArrayList<Product>();
        productHits.forEach(searchHit->{
            productMatches.add(searchHit.getContent());
        });

        System.out.println("Count:" +productMatches);
        productMatches.stream().forEach(System.out::println);
        int a =0;

    }
}
