package com.codenation.exceptions.handlers;

import com.codenation.exceptions.*;
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
  public final ResponseEntity<ExceptionSchema> handleEmptyRequestExceptions(EmptyRequestException ex) {
    ExceptionSchema exception = new ExceptionSchema(EmptyRequestException.getError(), EmptyRequestException.getErrorDescription());
    return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidLogException.class)
  public final ResponseEntity<ExceptionSchema> handleInvalidLogExceptions(InvalidLogException ex) {
    ExceptionSchema exception = new ExceptionSchema(InvalidLogException.getError(), InvalidLogException.getErrorDescription());
    return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidUserException.class)
  public final ResponseEntity<ExceptionSchema> handleInvalidUserExceptions(InvalidUserException ex) {
    ExceptionSchema exception = new ExceptionSchema(InvalidUserException.getError(), InvalidUserException.getErrorDescription());
    return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(LogNotFoundException.class)
  public final ResponseEntity<ExceptionSchema> handleLogNotFoundExceptions(LogNotFoundException ex) {
    ExceptionSchema exception = new ExceptionSchema(LogNotFoundException.getError(), LogNotFoundException.getErrorDescription());
    return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public final ResponseEntity<ExceptionSchema> handleUserNotFoundExceptions(UserNotFoundException ex) {
    ExceptionSchema exception = new ExceptionSchema(UserNotFoundException.getError(), UserNotFoundException.getErrorDescription());
    return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RoleNotFoundException.class)
  public final ResponseEntity<ExceptionSchema> handleRoleNotFoundExceptions(RoleNotFoundException ex) {
    ExceptionSchema exception = new ExceptionSchema(RoleNotFoundException.getError(), RoleNotFoundException.getErrorDescription());
    return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserNotPermittedException.class)
  public final ResponseEntity<ExceptionSchema> handleUserNotPermittedExceptions(UserNotPermittedException ex) {
    ExceptionSchema exception = new ExceptionSchema(UserNotPermittedException.getError(), UserNotPermittedException.getErrorDescription());
    return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ForgotPasswordUnderConstructionException.class)
  public final ResponseEntity<ExceptionSchema> handleForgotPasswordUnderConstructionExceptions(ForgotPasswordUnderConstructionException ex) {
    ExceptionSchema exception = new ExceptionSchema(ForgotPasswordUnderConstructionException.getError(), ForgotPasswordUnderConstructionException.getErrorDescription());
    return new ResponseEntity<>(exception, HttpStatus.NOT_IMPLEMENTED);
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  public final ResponseEntity<ExceptionSchema> handleUserAlreadyExistsExceptions(UserAlreadyExistsException ex) {
    ExceptionSchema exception = new ExceptionSchema(UserAlreadyExistsException.getError(), UserAlreadyExistsException.getErrorDescription());
    return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(SignUpException.class)
  public final ResponseEntity<ExceptionSchema> handleUserAlreadyExistsExceptions(SignUpException ex) {
    ExceptionSchema exception = new ExceptionSchema(SignUpException.getError(), ex.getErrorDescription());
    return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MainPageException.class)
  public final ResponseEntity<ExceptionSchema> handleMainPageExceptions(MainPageException ex) {
    ExceptionSchema exception = new ExceptionSchema(MainPageException.getError(), MainPageException.getErrorDescription());
    return new ResponseEntity<>(exception, HttpStatus.OK);
  }

  @ExceptionHandler(EnvironmentNotFoundException.class)
  public final ResponseEntity<ExceptionSchema> handleEnvironmentNotFoundExceptions(EnvironmentNotFoundException ex) {
    ExceptionSchema exception = new ExceptionSchema(EnvironmentNotFoundException.getError(), EnvironmentNotFoundException.getErrorDescription());
    return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(LevelNotFoundException.class)
  public final ResponseEntity<ExceptionSchema> handleLevelNotFoundExceptions(LevelNotFoundException ex) {
    ExceptionSchema exception = new ExceptionSchema(LevelNotFoundException.getError(), LevelNotFoundException.getErrorDescription());
    return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
  }


  @Override
  public final ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    ExceptionSchema exception = new ExceptionSchema("requisicao_invalida", "A validacao de dados falhou, verifique sua requisicao");
    return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ExceptionSchema> requestHandlingNoHandlerFound(NotFoundException ex) {
    ExceptionSchema exception = new ExceptionSchema(NotFoundException.getError(), NotFoundException.getErrorDescription());
    return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
  }


}