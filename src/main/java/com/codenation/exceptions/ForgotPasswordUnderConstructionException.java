package com.codenation.exceptions;

public class ForgotPasswordUnderConstructionException extends Exception {
  private static final String ERROR = "implementacao_em_andamento";
  private static final String ERROR_DESCRIPTION= "Esta funcionalidade ainda esta sob construcao. Caso tenha problemas, visite o nosso repositorio no Github https://github.com/codenation-dev/squad-2-ad-java-softplan-2";

  public static String getError() {
    return ERROR;
  }

  public static String getErrorDescription() {
    return ERROR_DESCRIPTION;
  }
}
