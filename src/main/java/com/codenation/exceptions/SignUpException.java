package com.codenation.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class SignUpException extends Exception {
  private String error ="problemas_no_cadastro";
  private List<String> error_description;

  public SignUpException(List<String> errors){
    this.error_description = new ArrayList<>(errors);
  }

  public String getError_description () {
    StringBuilder result = new StringBuilder();

    error_description.forEach(
            erro -> {
              result.append(erro)
                      .append("\n ");
            });
    return result.toString();
  }
}
