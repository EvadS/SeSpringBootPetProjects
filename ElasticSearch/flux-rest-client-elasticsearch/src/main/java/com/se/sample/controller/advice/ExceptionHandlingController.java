package com.se.sample.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlingController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("Invalid Request: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ErrorResponse<ErrorDetail>> handleWebExchangeBindException(WebExchangeBindException ex) {
        log.error("Validation error", ex);
        return Mono.just(new ErrorResponse<>(HttpStatus.BAD_REQUEST,
                ex.getBindingResult().getAllErrors().stream().map(this::toErrorDetail).collect(Collectors.toList())));
    }

    private ErrorDetail toErrorDetail(ObjectError objectError) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setField(objectError instanceof FieldError ? ((FieldError) objectError).getField() :
                objectError.getObjectName());
        errorDetail.setMessage(objectError.getDefaultMessage());
        return errorDetail;
    }
}
