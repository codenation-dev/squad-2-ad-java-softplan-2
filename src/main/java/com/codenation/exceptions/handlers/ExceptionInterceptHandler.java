package com.codenation.exceptions.handlers;

import com.codenation.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionInterceptHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EmptyRequestException.class)
  public final ResponseEntity<Object> handleEmptyRequestExceptions(EmptyRequestException ex) {
    ExceptionDTO exception = new ExceptionDTO(ex.getError(), ex.getError_description());
    return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidLogException.class)
  public final ResponseEntity<Object> handleInvalidLogExceptions(EmptyRequestException ex) {
    ExceptionDTO exception = new ExceptionDTO(ex.getError(), ex.getError_description());
    return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidUserException.class)
  public final ResponseEntity<Object> handleInvalidUserExceptions(InvalidUserException ex) {
    ExceptionDTO exception = new ExceptionDTO(ex.getError(), ex.getError_description());
    return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
  }
}