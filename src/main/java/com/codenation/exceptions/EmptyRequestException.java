package com.codenation.exceptions;

public class EmptyRequestException extends Exception {
  private static final String ERROR = "resquisicao_nula";
  private static final String ERROR_DESCRIPTION= "A requisicao esta vazia ou contem dados invalidos";

  public static String getError() {
    return ERROR;
  }

  public static String getErrorDescription() {
    return ERROR_DESCRIPTION;
  }
}