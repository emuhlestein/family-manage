package com.intelliviz.resourcemanagement.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

/**
 * RestController for handling all exceptions.
 */
// For more info, look at userauthdemo project
@ControllerAdvice
@RestController
public class ExceptionHandling extends ResponseEntityExceptionHandler {
    public static final String VALIDATION_FAILED = "Validation failed";

    private static Logger LOGGER = LogManager.getLogger(ExceptionHandling.class);

    @ExceptionHandler(MissingNameException.class)
    public ResponseEntity<ExceptionResponse> missingNameException(MissingNameException exception) {
        LOGGER.info("In missingNameException");
        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(DuplicateNameException.class)
    public ResponseEntity<ExceptionResponse> duplicateNameException(DuplicateNameException exception) {
        LOGGER.info("In duplicateNameException");
        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(ProductTypeNotFoundException.class)
    public ResponseEntity<ExceptionResponse> productTypeNotFoundException(ProductTypeNotFoundException exception) {
        LOGGER.info("In duplicateNameException");
        return createHttpResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String message = "";
        if(fieldErrors.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(fieldErrors.get(0).getDefaultMessage());
            sb.append(": ");
            sb.append(fieldErrors.get(0).getField());
            message = sb.toString();
        }
        return new ResponseEntity(new ExceptionResponse(status.value(), HttpStatus.BAD_REQUEST, message, VALIDATION_FAILED), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ExceptionResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        // body (httpResponse) and status
        return new ResponseEntity<>(new ExceptionResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);
    }
}
