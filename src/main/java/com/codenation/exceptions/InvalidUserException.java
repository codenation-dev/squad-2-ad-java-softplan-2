package com.codenation.exceptions;

public class InvalidUserException extends Exception {
  private static final String ERROR ="usuario_invalido";
  private static final String ERROR_DESCRIPTION= "Os dados de usuario sao invalidos, por favor, verifique";

  public static String getError() {
    return ERROR;
  }

  public static String getErrorDescription() {
    return ERROR_DESCRIPTION;
  }
}
