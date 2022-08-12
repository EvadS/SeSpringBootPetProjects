package com.se.sample.rest.client.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.se.sample.rest.client.component.RestTemplateResponseErrorHandler;
import com.se.sample.rest.client.component.ValidatableRestTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class BeansConfiguration {

    Log log = LogFactory.getLog(getClass());

    @Bean
    ValidatableRestTemplate restTemplate(Validator validator, ObjectMapper objectMapper) {

        ClientHttpRequestInterceptor interceptor = (HttpRequest request, byte[] body,
                                                    ClientHttpRequestExecution execution) -> {
            log.debug(String.format("Request to Uri %s with HTTP verb '%s'",
                    request.getURI(), request.getMethod().toString()));
            return execution.execute(request, body);
        };

        ValidatableRestTemplate monstaRestTemplate = new ValidatableRestTemplate(validator, objectMapper);

        List<ClientHttpRequestInterceptor> interceptors
                = monstaRestTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(interceptor);
        monstaRestTemplate.setInterceptors(interceptors);

        return monstaRestTemplate;
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.build();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }
}
