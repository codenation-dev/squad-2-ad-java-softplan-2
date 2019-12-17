package com.codenation.exceptions;

public class EnvironmentNotFoundException extends Exception {
  private static final String ERROR = "environment_nao_encontrado";
  private static final String ERROR_DESCRIPTION= "O campo \"environment\" deve ser um dos tipos: [DEVELOPMENT, STAGE, PRODUCTION]";

  public static String getError() {
    return ERROR;
  }

  public static String getErrorDescription() {
    return ERROR_DESCRIPTION;
  }
}

