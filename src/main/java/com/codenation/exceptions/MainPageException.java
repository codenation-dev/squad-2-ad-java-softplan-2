package com.codenation.exceptions;

public class MainPageException extends Exception {
  private static final String ERROR = "bem_vindo";
  private static final String ERROR_DESCRIPTION= "Para comecar, visite nosso repositorio no "+
          "GitHub https://github.com/codenation-dev/squad-2-ad-java-softplan-2 ou confira a documentacao em /swagger-ui.html";

  public static String getError() {
    return ERROR;
  }

  public static String getErrorDescription() {
    return ERROR_DESCRIPTION;
  }
}
