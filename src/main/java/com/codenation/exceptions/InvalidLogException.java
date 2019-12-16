package com.codenation.exceptions;

public class InvalidLogException extends Exception {
  private static final String ERROR = "log_invalido";
  private static final String ERROR_DESCRIPTION= "Os dados do log informado sao invalidos, por favor, verifique";

  public static String getError() {
    return ERROR;
  }

  public static String getErrorDescription() {
    return ERROR_DESCRIPTION;
  }
}

