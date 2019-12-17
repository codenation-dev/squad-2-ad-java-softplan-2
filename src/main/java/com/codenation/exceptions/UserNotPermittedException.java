package com.codenation.exceptions;

public class UserNotPermittedException extends Exception {

  private static final String ERROR = "usuario_nao_permitido";
  private static final String ERROR_DESCRIPTION= "A solicitacao de alteracao de dados de usuario deve ser feita pelo proprio ou Administrador";

  public static String getError() {
    return ERROR;
  }

  public static String getErrorDescription() {
    return ERROR_DESCRIPTION;
  }
}
