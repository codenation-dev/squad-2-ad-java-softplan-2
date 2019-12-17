package com.codenation.exceptions;

public class LevelNotFoundException extends Exception {
  private static final String ERROR = "level_nao_encontrada";
  private static final String ERROR_DESCRIPTION= "O campo \"level\" deve ser um dos tipos: [DEBUG, WARNING ERROR]";

  public static String getError() {
    return ERROR;
  }

  public static String getErrorDescription() {
    return ERROR_DESCRIPTION;
  }
}

