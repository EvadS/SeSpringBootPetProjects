package com.se.sample;

import com.se.sample.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;

@Slf4j
@SpringBootApplication
@EnableReactiveElasticsearchRepositories

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

    @Bean
    CommandLineRunner run(UserRepository personRepository) {
        return args -> {
            log.info("Application started");
        };
    }
}
