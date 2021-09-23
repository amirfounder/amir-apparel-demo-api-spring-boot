package com.amirfounder.amirappareldemoapispringboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionController {

    private static final String NOT_FOUND = "404 Resource Not Found";
    private static final String SERVER_ERROR = "500 Server Error";
    private static final String SERVICE_UNAVAILABLE = "503 Service Unavailable";
    private static final String BAD_REQUEST = "400 Bad Request";

    @ExceptionHandler(ResourceNotFound.class)
    protected ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFound exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                NOT_FOUND,
                exception.getMessage()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerError.class)
    protected ResponseEntity<ExceptionResponse> internalServerError(InternalServerError exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                SERVER_ERROR,
                exception.getMessage()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServiceUnavailable.class)
    protected ResponseEntity<ExceptionResponse> serviceUnavailable(ServiceUnavailable exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                SERVICE_UNAVAILABLE,
                exception.getMessage()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(BadRequest.class)
    protected ResponseEntity<ExceptionResponse> badRequest(BadRequest exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                BAD_REQUEST,
                exception.getMessage()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
