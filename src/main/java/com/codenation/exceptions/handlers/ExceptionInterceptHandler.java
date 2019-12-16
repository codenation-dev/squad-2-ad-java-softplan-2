package com.codenation.exceptions.handlers;

import com.codenation.exceptions.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionInterceptHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EmptyRequestException.class)
  public final ResponseEntity<Object> handleEmptyRequestExceptions(EmptyRequestException ex) {
    ExceptionDTO exception = new ExceptionDTO(ex.getError(), ex.getError_description());
    return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidLogException.class)
  public final ResponseEntity<Object> handleInvalidLogExceptions(InvalidUserException ex) {
    ExceptionDTO exception = new ExceptionDTO(ex.getError(), ex.getError_description());
    return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidUserException.class)
  public final ResponseEntity<Object> handleInvalidUserExceptions(InvalidUserException ex) {
    ExceptionDTO exception = new ExceptionDTO(ex.getError(), ex.getError_description());
    return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(LogNotFoundException.class)
  public final ResponseEntity<Object> handleLogNotFoundExceptions(LogNotFoundException ex) {
    ExceptionDTO exception = new ExceptionDTO(ex.getError(), ex.getError_description());
    return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public final ResponseEntity<Object> handleUserNotFoundExceptions(UserNotFoundException ex) {
    ExceptionDTO exception = new ExceptionDTO(ex.getError(), ex.getError_description());
    return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RoleNotFoundException.class)
  public final ResponseEntity<Object> handleRoleNotFoundExceptions(RoleNotFoundException ex) {
    ExceptionDTO exception = new ExceptionDTO(ex.getError(), ex.getError_description());
    return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
  }

  @Override
  public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    ExceptionDTO exception = new ExceptionDTO("requisicao_invalida", "A validacao de dados falhou, verifique sua requisicao");
    return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
  }

}