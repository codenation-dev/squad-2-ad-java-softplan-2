package com.codenation.exceptions;

import java.util.ArrayList;
import java.util.List;


public class SignUpException extends Exception {
  private static final String ERROR ="problemas_no_cadastro";
  private static final List<String> ERROR_DESCRIPTION = new ArrayList<>();

  public SignUpException(List<String> errors){
    ERROR_DESCRIPTION.addAll(errors);
  }

  public String getErrorDescription() {
    StringBuilder result = new StringBuilder();

    ERROR_DESCRIPTION.forEach(
            erro ->
              result.append(erro)
                      .append("\n ")
            );
    return result.toString();
  }

  public static String getError() {
    return ERROR;
  }
}
