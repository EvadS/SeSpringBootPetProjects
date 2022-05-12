package com.example.filedemo.config;

import com.example.filedemo.model.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {
    Logger log = LoggerFactory.getLogger(APIExceptionHandler.class);
//

//    @Override
//    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//       log.info("+++++++++++++++++++++++++++++++++++++++");
//        log.error(ex.getMessage(), ex);
//
//        ResponseDTO responseDTO = new  ResponseDTO(
//                (HttpStatus.BAD_REQUEST.toString()),
//                (ex.getMessage()));
//
//        return ResponseEntity.badRequest().body(responseDTO);
//
//    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(
                ex, apiError, headers, /*apiError.getStatus()*/HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));

        ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                ex.getLocalizedMessage(), Collections.singletonList(builder.substring(0, builder.length() - 2)));
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST//apiError.getStatus()
        );
    }



    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
       return null;
}

// HANDLE ALL EXCEPTIONS
//    @ExceptionHandler({ Exception.class })
//    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
//     int aaa =10;
//        ApiError apiError = new ApiError(
//                HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), Collections.singletonList("error occurred"));
//        return new ResponseEntity<Object>(
//                apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST/*apiError.getStatus()*/);
//    }


    @ExceptionHandler({ UnexpectedTypeException.class })
    public ResponseEntity<Object> handleAllUnexp(UnexpectedTypeException ex, WebRequest request) {
        int aaa =10;
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), Collections.singletonList("error occurred"));
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST/*apiError.getStatus()*/);
    }
}