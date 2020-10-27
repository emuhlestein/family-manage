package com.intelliviz.resourcemanagement.exception;

import com.intelliviz.resourcemanagement.repository.ProductTypeJpaRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ExceptionHandling extends ResponseEntityExceptionHandler {

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

    private ResponseEntity<ExceptionResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        // body (httpResponse) and status
        return new ResponseEntity<>(new ExceptionResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);
    }
}
