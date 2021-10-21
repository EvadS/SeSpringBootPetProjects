package com.se.sample.errors;

import com.se.sample.errors.exception.AlreadyExistException;
import com.se.sample.errors.exception.ResourceNotFoundException;
import com.se.sample.errors.model.ApiValidationError;
import com.se.sample.errors.model.ErrorDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.codec.DecodingException;
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
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Allows to handle all expected and unexpected errors occurred while processing the request.
 */
@RestControllerAdvice
//@ControllerAdvice
public class ExceptionControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    private static final String APPLICATION_PROBLEM_JSON = "application/problem+json";

    // work but incorrect
    @ExceptionHandler({WebExchangeBindException.class,
            ConstraintViolationException.class
    })
    public HttpEntity<ErrorDetail> handleWebExchangeBindException(WebExchangeBindException exception) {

        LOGGER.error("Constraint Violation: {}", exception.getMessage());

        List<ApiValidationError> validationErrorList = exception.getFieldErrors().stream()
                .map(i -> new ApiValidationError(i.getObjectName(),
                        i.getField(),
                        i.getRejectedValue(),
                        i.getDefaultMessage()))
                .collect(Collectors.toList());

        return validationError(exception.getReason(), validationErrorList);
    }

    /**
     * Handles incorrect json case
     *
     * @param e any exception of type {@link Exception}
     * @return {@link ResponseEntity} containing standard body in case of errors
     */
    @ExceptionHandler(DecodingException.class)
    public HttpEntity<ErrorDetail> handleEmptyResultDataAccessException(DecodingException e) {

        LOGGER.error("Incorrect json : {}", e.getMessage());

        ErrorDetail problem = new ErrorDetail("Resource not found",
                "Requested resource cannot be found");
        problem.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(problem, overrideContentType(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles a case when requested resource cannot be found
     *
     * @param e any exception of type {@link Exception}
     * @return {@link ResponseEntity} containing standard body in case of errors
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public HttpEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException e) {

        LOGGER.error("Resource {} ,by: {} value {} cannot be found", e.getResourceName(),
                e.getFieldName(), e.getFieldValue());

        ErrorDetail problem = new ErrorDetail("Resource not found",
                e.getMessage());
        problem.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(problem, overrideContentType(), HttpStatus.NOT_FOUND);
    }


    /**
     * Handles a case when requested resource cannot be created according to dublicates
     *
     * @param e any exception of type {@link Exception}
     * @return {@link ResponseEntity} containing standard body in case of errors
     */
    @ExceptionHandler(AlreadyExistException.class)
    public HttpEntity<ErrorDetail> handleAlreadyExistException(AlreadyExistException e) {

        LOGGER.error("Resource {} ,by: {} value {} already exists", e.getResourceName(),
                e.getFieldName(), e.getFieldValue());

        ErrorDetail problem = new ErrorDetail("Resource already exists",
                e.getMessage());
        problem.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(problem, overrideContentType(), HttpStatus.NOT_FOUND);
    }

    /*******************************************************
     * 415 NOT FOUND BLOCK
     *******************************************************/

    /**
     * Handles a case when requested resource cannot be resolved according to exists apis
     *
     * @param e any exception of type {@link Exception}
     * @return {@link ResponseEntity} containing standard body in case of errors
     */
    @ExceptionHandler(org.springframework.web.server.MethodNotAllowedException.class)
    public HttpEntity<ErrorDetail> handleRequestMethodNotSupportedException(MethodNotAllowedException e) {

        LOGGER.error("Method not allowed : {}", e.getMessage());

        final ErrorDetail problem = new ErrorDetail("Invalid request", e.getMessage());
        problem.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(problem, overrideContentType(), HttpStatus.BAD_REQUEST);
    }


    /**
     * 415 block UnsupportedMediaTypeStatusException
     */

    @ExceptionHandler(UnsupportedMediaTypeStatusException.class)
    public HttpEntity<ErrorDetail> handleUnsupportedMediaTypeStatusException(UnsupportedMediaTypeStatusException e) {

        LOGGER.error("UnsupportedMediaTypeStatusException ");

        ErrorDetail problem = new ErrorDetail("Incorrect media type",
                e.getMessage());
        problem.setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());

        return new ResponseEntity<>(problem, overrideContentType(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
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
     * @param e any exception of type {@link MethodArgumentTypeMismatchException}
     * @return {@link ResponseEntity} containing standard body in case of errors
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public HttpEntity<ErrorDetail> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e) {

        LOGGER.error("Request body is invalid: {}", e.getMessage());

        final ErrorDetail problem = new ErrorDetail("Field type mismatch", null);
        problem.setStatus(HttpStatus.BAD_REQUEST.value());

        return null;
//        problem.setErrors(Collections.singletonList(new ErrorDetail("Wrong field value format",
//                String.format("Incorrect value '%s' for field '%s'. Expected value type '%s'",
//                        e.getValue(), e.getName(), e.getParameter().getParameterType().getTypeName()))));
//
//        return new ResponseEntity<>(problem, overrideContentType(), HttpStatus.BAD_REQUEST);
    }


//    @ExceptionHandler(value = { NoHandlerFoundException.class })
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ApiErrorResponse noHandlerFoundException(Exception ex) {
//        LOG.error(ex.getCause().toString());
//        return new ApiErrorResponse(404, "Resource Not Found");
//    }




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
     * @param e any exception of type {@link Exception}
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

    public HttpEntity<ErrorDetail> validationError(final String exceptionMessage,
                                                   final List<ApiValidationError> details) {

        final ErrorDetail problem = new ErrorDetail("Field type mismatch", exceptionMessage);
        problem.setDetail("Constraint validation");
        problem.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        problem.setErrors(details);

        return new ResponseEntity<>(problem, overrideContentType(), HttpStatus.BAD_REQUEST);
    }
}
