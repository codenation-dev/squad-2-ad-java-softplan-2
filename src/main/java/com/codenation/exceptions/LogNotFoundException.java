package com.codenation.exceptions;

public class LogNotFoundException extends Exception {
  private static final String ERROR = "log_nao_encontrado";
  private static final String ERROR_DESCRIPTION= "O log nao foi encontrado. Pode ter sido movido ou excluido";

  public static String getError() {
    return ERROR;
  }

  public static String getErrorDescription() {
    return ERROR_DESCRIPTION;
  }
}

