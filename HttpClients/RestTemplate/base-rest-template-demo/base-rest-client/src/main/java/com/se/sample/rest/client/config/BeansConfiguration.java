package com.se.sample.rest.client.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.validation.Validator;

@Configuration
public class BeansConfiguration {

    @Bean
    RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
