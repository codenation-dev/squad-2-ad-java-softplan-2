package com.codenation.exceptions;

public class UserNotFoundException extends Exception {
  private static final String ERROR = "usuario_nao_encontrado";
  private static final String ERROR_DESCRIPTION= "O usuário nao foi encontrado. Pode ter sido movido ou excluido";

  public static String getError() {
    return ERROR;
  }

  public static String getErrorDescription() {
    return ERROR_DESCRIPTION;
  }
}

