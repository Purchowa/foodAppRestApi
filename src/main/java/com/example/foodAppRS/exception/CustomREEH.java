package com.example.foodAppRS.exception;

import com.example.foodAppRS.exception.types.FridgeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * CustomResponseEntityExceptionHandler
 * @see ResponseEntityExceptionHandler
 */
@ControllerAdvice
@RestController
public class CustomREEH extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest){
        ExceptionResponse exceptionResponse= new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FridgeNotFoundException.class)
    public final ResponseEntity<Object> handleFridgeNotFound(Exception ex, WebRequest webRequest){
        ExceptionResponse exceptionResponse= new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
