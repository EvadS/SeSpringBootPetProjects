package com.se.sample.rest.client.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.rest.client.exception.ResourceUnavailableException;
import com.se.sample.rest.client.exception.RestServerException;
import com.se.sample.rest.client.exception.RestTemplateException;
import com.se.sample.rest.client.model.error.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.net.URI;
import java.util.Set;

public class ValidatableRestTemplate extends RestTemplate {
    private final Logger logger = LoggerFactory.getLogger(ValidatableRestTemplate.class);
    private final ObjectMapper objectMapper;
    private final Validator validator;


    public ValidatableRestTemplate(Validator validator, ObjectMapper objectMapper) {
        this.validator = validator;
        this.objectMapper = objectMapper;
    }

    @Override
    protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback,
                              ResponseExtractor<T> responseExtractor) throws RestClientException {

        try {
            final T response = super.doExecute(url, method, requestCallback, responseExtractor);

            Object body;
            if (response instanceof ResponseEntity<?>) {
                body = ((ResponseEntity) response).getBody();
            } else {
                body = response;
            }

            // validation response here !!!
            final Set<ConstraintViolation<Object>> violations = validator.validate(body);
            if (violations.isEmpty()) {
                return response;
            }
            throw new ConstraintViolationException("Invalid response", violations);
        } catch (ResourceAccessException e) {
            throw new ResourceUnavailableException(e.getMessage());
        } catch (HttpStatusCodeException ex) {

            String json = ex.getResponseBodyAsString();
            ApiError errorResponse = null;
            try {
                errorResponse = objectMapper.readValue(json, ApiError.class);
                // Have no time to use logger
                throw new RestTemplateException(errorResponse);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            // TODO: throw my exception wit correct message from errorResponse
            throw new RestServerException(json);
        }
    }
}

