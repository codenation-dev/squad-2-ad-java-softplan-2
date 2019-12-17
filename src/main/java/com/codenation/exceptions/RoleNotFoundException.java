package com.codenation.exceptions;

public class RoleNotFoundException extends Exception {
  private static final String ERROR = "role_nao_encontrada";
  private static final String ERROR_DESCRIPTION= "A role informada nao foi encontrada";

  public static String getError() {
    return ERROR;
  }

  public static String getErrorDescription() {
    return ERROR_DESCRIPTION;
  }
}

