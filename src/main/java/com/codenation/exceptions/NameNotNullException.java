package com.codenation.exceptions;

public class NameNotNullException extends Exception {
  private static final String ERROR = "nome_nulo_ou_curto";
  private static final String ERROR_DESCRIPTION= "O nome deve conter no minimo 5 caracteres";

  public static String getError() {
    return ERROR;
  }

  public static String getErrorDescription() {
    return ERROR_DESCRIPTION;
  }
}
