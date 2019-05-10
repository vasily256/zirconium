package com.bellintegrator.zirconium.controller;

import com.bellintegrator.zirconium.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Обработчик исключений для формирования
 * соответствующих ответов сервера
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ErrorResponseBody body = new ErrorResponseBody(ex.getMessage());
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorResponseBody body = new ErrorResponseBody(ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<?> handleAllExceptions(Throwable ex) {
        StringWriter strWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(strWriter));
        ErrorResponseBody body = new ErrorResponseBody(strWriter.toString());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
