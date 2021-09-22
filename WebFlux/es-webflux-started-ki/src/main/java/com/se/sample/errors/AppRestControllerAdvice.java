package com.se.sample.errors;



import com.se.sample.errors.exception.ResourceNotFoundException;
import com.se.sample.errors.exception.UserAlreadyExistException;
import com.se.sample.errors.exception.UserNotFoundException;
import com.se.sample.errors.exception.WrongCredentialException;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.se.sample.errors.Error.USER_ALREADY_EXIST;
import static com.se.sample.errors.Error.WRONG_CREDENTIALS;
import static com.se.sample.errors.Error.USER_NOT_FOUND;


@Log4j2
@RestControllerAdvice
public class AppRestControllerAdvice implements ErrorWebExceptionHandler {


@ExceptionHandler(value = ResourceNotFoundException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
public Error notfoundException(final WrongCredentialException e) {
   log.info("=============== notfoundException");
    return WRONG_CREDENTIALS;
}

    @ExceptionHandler(value = WrongCredentialException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Error wrongCredentialException(final WrongCredentialException e) {
        return WRONG_CREDENTIALS;
    }

    @ExceptionHandler(value = UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Error wrongCredentialException(final UserAlreadyExistException e) {
        return USER_ALREADY_EXIST;
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Error wrongCredentialException(final UserNotFoundException e) {
        return USER_NOT_FOUND;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        return null;
    }
}
