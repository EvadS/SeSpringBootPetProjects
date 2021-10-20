package com.se.sample.errors;


import com.se.sample.errors.exception.ResourceNotFoundException;
import com.se.sample.errors.model.ErrorDetail;
import com.se.sample.errors.model.GlobalException;
import lombok.Data;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.List;
import java.util.Map;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * Excepption handling from functional flow
 */
@Configuration
@Order(-2)
public class GlobalErrorAttributes implements ErrorWebExceptionHandler{

    private ObjectMapper objectMapper;

    public GlobalErrorAttributes(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {

        DataBufferFactory bufferFactory = serverWebExchange.getResponse().bufferFactory();
    //TODO: Worked for test-->
        if (throwable instanceof ConstraintViolationException){

            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) throwable).getConstraintViolations();
            List<ErrorDetail> invalid_parameter = constraintViolations.stream()
                    .map(violation -> new ErrorDetail("Invalid Parameter", violation.getMessage()))
                    .collect(Collectors.toList());

            ErrorDetail errorDetail = new ErrorDetail();
            errorDetail.setTitle("Field type mismatch");
            errorDetail.setDetail(throwable.getMessage());
            errorDetail.setErrors(invalid_parameter);
            errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());

            DataBuffer dataBuffer = null;
            try {
                dataBuffer = bufferFactory.wrap(objectMapper.writeValueAsBytes(errorDetail));
            } catch (JsonProcessingException e) {
                dataBuffer = bufferFactory.wrap("".getBytes());
            }

            serverWebExchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

            return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
        }

        //<-- For test
            if (throwable instanceof IOException) {
            serverWebExchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            DataBuffer dataBuffer = null;
            try {
                dataBuffer = bufferFactory.wrap(objectMapper.writeValueAsBytes(new HttpError("Custom message")));
            } catch (JsonProcessingException e) {
                dataBuffer = bufferFactory.wrap("".getBytes());
            }
            serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
        }

        serverWebExchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        serverWebExchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        DataBuffer dataBuffer = bufferFactory.wrap("Unknown error".getBytes());
        //TODO: for learning -->
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        boolean committed = serverWebExchange.getResponse().isCommitted();
        // <--for learning

        return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
    }

//    @Override
//    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
//        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
//        AppError appError = ErrorCode.GENERIC.toAppError();
//
//        if (ex instanceof AppException) {
//            AppException ae = (AppException) ex;
//            status = ae.getStatusCode();
//            appError = new AppError(ae.getCode(), ae.getText());
//
//            log.debug(appError.toString());
//        } else {
//            log.error(ex.getMessage(), ex);
//        }
//
//        if (exchange.getResponse().isCommitted()) {
//            return Mono.error(ex);
//        }
//
//        exchange.getResponse().setStatusCode(status);
//        return bufferWriter.write(exchange.getResponse(), appError);
//    }

    public class HttpError {

        private String message;

        HttpError(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

}