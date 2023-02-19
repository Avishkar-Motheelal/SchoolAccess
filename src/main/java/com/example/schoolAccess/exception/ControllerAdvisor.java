package com.example.schoolAccess.exception;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor {
    @ApiResponse(responseCode = "404")
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionBody> handleNotFoundException(NotFoundException exception) {
        String dateTime = OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        ExceptionBody body = new ExceptionBody(dateTime, exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


    @ApiResponse(responseCode = "400")
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionBody> handleValidationException(ValidationException exception) {
        String dateTime = OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        ExceptionBody body = new ExceptionBody(dateTime, exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    @ApiResponse(responseCode = "400")
    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<ExceptionBody>> handleBindException(BindException exception) {
        String dateTime = OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        List<ExceptionBody> exceptions = exception.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .map(message -> new ExceptionBody(dateTime, message))
            .toList();
        return new ResponseEntity<>(exceptions, HttpStatus.BAD_REQUEST);
    }
}
