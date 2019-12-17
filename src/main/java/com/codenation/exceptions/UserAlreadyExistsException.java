package com.codenation.exceptions;

public class UserAlreadyExistsException extends Exception {
  private static final String ERROR = "usuario_existente";
  private static final String ERROR_DESCRIPTION= "Este e-mail ja esta cadastrado";

  public static String getError() {
    return ERROR;
  }

  public static String getErrorDescription() {
    return ERROR_DESCRIPTION;
  }
}
