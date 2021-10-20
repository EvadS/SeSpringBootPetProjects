package com.se.sample.errors;

import com.se.sample.errors.exception.ResourceNotFoundException;
import com.se.sample.errors.model.ErrorDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 *  Allows to handle all expected and unexpected errors occurred while processing the request.
 *
 */
@RestControllerAdvice
//@ControllerAdvice
public class ExceptionControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    private static final String APPLICATION_PROBLEM_JSON = "application/problem+json";

    /**
     * Handles a case when requested resource cannot be found
     *
     * @param e
     *           any exception of type {@link Exception}
     * @return {@link ResponseEntity} containing standard body in case of errors
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public HttpEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException e) {

        LOGGER.error("Resource {} ,by: {} value {} cannot be found", e.getResourceName(),
                e.getFieldName(), e.getFieldValue());

        ErrorDetail problem = new ErrorDetail("Resource not found",
                "Requested resource cannot be found");
        problem.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(problem, overrideContentType(), HttpStatus.NOT_FOUND);
    }

    //TODO: HERE--------------
    // work but incorrect
    @ExceptionHandler(WebExchangeBindException.class)
    public HttpEntity<ErrorDetail> handleWebExchangeBindException(
            WebExchangeBindException e) {

        LOGGER.error("Constraint Violation: {}", e.getMessage());

        return validationError(e.getMessage(), e.getFieldErrors().stream()
                .map(violation -> new ErrorDetail("Invalid Parameter", violation.getField()))
                .collect(Collectors.toList()));
    }

    /**
     * Handles a case when message from request body cannot be de-serialized
     *
     * @param e
     *           any exception of type {@link HttpMessageNotReadableException}
     * @return {@link ResponseEntity} containing standard body in case of errors
     */
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public HttpEntity<ErrorDetail> handleHttpMessageNotReadableException(
//            HttpMessageNotReadableException e, final HttpServletRequest request) {
//
//        LOGGER.error("Cannot de-serialize message in request body: {}", e.getMessage());
//
//        final ErrorDetail problem = new ErrorDetail("Invalid request", "Input Request Message cannot be processed");
//        problem.setStatus(HttpStatus.BAD_REQUEST.value());
//
//        return new ResponseEntity<>(problem, overrideContentType(), HttpStatus.BAD_REQUEST);
//    }

    /**
     * Handles a case when validation of the request body fails
     *
     * @param e
     *           any exception of type {@link MethodArgumentNotValidException}
     * @return {@link ResponseEntity} containing standard body in case of errors
     */
//    @ExceptionHandler(AccessDeniedException.class)
//    public HttpEntity<ErrorDetail> handleAccessDeniedException(
//            AccessDeniedException e, final HttpServletRequest request) {
//
//        LOGGER.error("Access Denied: {}", e.getMessage());
//
//        final ErrorDetail problem = new ErrorDetail(
//                "Access Denied - You do not have permission to access the operation",
//                e.getMessage());
//        problem.setStatus(HttpStatus.FORBIDDEN.value());
//
//        return new ResponseEntity<>(problem, overrideContentType(), HttpStatus.FORBIDDEN);
//    }

    /**
     * Handles a case when there are business Exceptions

     */
//    @ExceptionHandler(BusinessException.class)
//    public HttpEntity<ErrorDetail> handleBusinessException(
//            AccessDeniedException e, final HttpServletRequest request) {
//
//        LOGGER.error("Business Exception: {}", e.getMessage());
//
//        final ErrorDetail problem = new ErrorDetail(
//                "Business Exception",
//                e.getMessage());
//        problem.setStatus(HttpStatus.BAD_REQUEST.value());
//
//        return new ResponseEntity<>(problem, overrideContentType(), HttpStatus.BAD_REQUEST);
//    }

    /**
     * Handles a case when validation of the request body fails
     *
     * @param e
     *           any exception of type {@link MethodArgumentTypeMismatchException}
     * @return {@link ResponseEntity} containing standard body in case of errors
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public HttpEntity<ErrorDetail> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e) {

        LOGGER.error("Request body is invalid: {}", e.getMessage());

        final ErrorDetail problem = new ErrorDetail("Field type mismatch", null);
        problem.setStatus(HttpStatus.BAD_REQUEST.value());
        problem.setErrors(Collections.singletonList(new ErrorDetail("Wrong field value format",
                String.format("Incorrect value '%s' for field '%s'. Expected value type '%s'",
                        e.getValue(), e.getName(), e.getParameter().getParameterType().getTypeName()))));

        return new ResponseEntity<>(problem, overrideContentType(), HttpStatus.BAD_REQUEST);
    }



//    @ExceptionHandler(value = { NoHandlerFoundException.class })
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ApiErrorResponse noHandlerFoundException(Exception ex) {
//        LOG.error(ex.getCause().toString());
//        return new ApiErrorResponse(404, "Resource Not Found");
//    }

    @ExceptionHandler(ConstraintViolationException.class)
    public HttpEntity<ErrorDetail> handleConstraintViolationsFromJavax(
            javax.validation.ConstraintViolationException e) {

        LOGGER.error("Constraint Violation: {}", e.getMessage());

        //return new ApiErrorResponse(400, "Bad Request");
        return validationError(e.getMessage(), e.getConstraintViolations().stream()
                .map(violation -> new ErrorDetail("Invalid Parameter", violation.getMessage()))
                .collect(Collectors.toList()));
    }

    public HttpEntity<ErrorDetail> validationError(final String exceptionMessage,
                                                   final List<ErrorDetail> details) {

        LOGGER.debug("Request body is invalid: {}", exceptionMessage);

        final ErrorDetail problem = new ErrorDetail("Field type mismatch", exceptionMessage);
        problem.setStatus(HttpStatus.BAD_REQUEST.value());
        problem.setErrors(details);

        return new ResponseEntity<>(problem, overrideContentType(), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(EmptyResultDataAccessException.class)
//    public HttpEntity<ErrorDetail> handleEmptyResultDataAccessException(
//            EmptyResultDataAccessException e, final HttpServletRequest request) {
//
//        LOGGER.error("EmptyResultDataAccessException: {}", e.getMessage());
//
//        ErrorDetail problem = new ErrorDetail("Resource not found",
//                "Requested resource cannot be found");
//        problem.setStatus(HttpStatus.BAD_REQUEST.value());
//
//        return new ResponseEntity<>(problem, overrideContentType(), HttpStatus.BAD_REQUEST);
//    }

//    @ExceptionHandler(SQLSyntaxErrorException.class)
//    public HttpEntity<ErrorDetail> handleSQLSyntaxErrorExceptionException(
//            SQLSyntaxErrorException e, final HttpServletRequest request) {
//
//        LOGGER.error("ObjectOptimisticLockingFailureException: {}", e.getMessage());
//
//        ErrorDetail problem = new ErrorDetail("Invalid Data Fetch",
//                e.getMessage());
//        problem.setStatus(HttpStatus.LOCKED.value());
//
//        return new ResponseEntity<>(problem, overrideContentType(), HttpStatus.LOCKED);
//    }

//    /**
//     * Handles {@link InvalidResourceException}
//     *
//     * @param e any exception of type {@link InvalidResourceException}
//     * @return {@link ResponseEntity} containing standard body in case of errors
//     */
//    @ExceptionHandler(InvalidResourceException.class)
//    public HttpEntity<ErrorDetail> handleInvalidAssetResourceException(InvalidResourceException e,
//                                                                       final HttpServletRequest request) {
//
//        LOGGER.debug("Invalid resource provided: {}", e.getMessage());
//
//        final ErrorDetail problem = new ErrorDetail("Invalid resource provided", e.getMessage());
//        problem.setStatus(HttpStatus.BAD_REQUEST.value());
//
//        return new ResponseEntity<>(problem, overrideContentType(), HttpStatus.BAD_REQUEST);
//    }

    /**
     * Handles all unexpected situations
     *
     * @param e
     *           any exception of type {@link Exception}
     * @return {@link ResponseEntity} containing standard body in case of errors
     */
    @ExceptionHandler(Exception.class)
    public HttpEntity<ErrorDetail> handleException(Exception e) {

        LOGGER.error("An unexpected error occurred", e);

        ErrorDetail problem = new ErrorDetail("Internal Error",
                "An unexpected error has occurred");
        problem.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(problem, overrideContentType(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private HttpHeaders overrideContentType() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", APPLICATION_PROBLEM_JSON);
        return httpHeaders;
    }
}