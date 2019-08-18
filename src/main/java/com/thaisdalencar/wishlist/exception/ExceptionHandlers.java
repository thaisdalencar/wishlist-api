package com.thaisdalencar.wishlist.exception;

import com.thaisdalencar.wishlist.service.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        return handleException(NOT_FOUND, ex, request);
    }

    @ExceptionHandler(InvalidProductException.class)
    protected ResponseEntity<Object> handleInvalidProduct(RuntimeException ex, WebRequest request) {
        return handleException(BAD_REQUEST, ex, request);
    }

    private ResponseEntity<Object> handleException(HttpStatus httpStatus, RuntimeException ex, WebRequest request) {
        var bodyOfResponse = new ApiError(httpStatus, ex.getMessage());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), httpStatus, request);
    }
}
