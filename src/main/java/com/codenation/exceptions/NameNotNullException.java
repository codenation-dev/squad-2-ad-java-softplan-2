package com.codenation.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NameNotNullException extends Exception {

  private String error = "nome_nulo";
  private String error_description = "O nome informado é nulo ou invalido";

  public NameNotNullException(String error, String error_description) {
    this.error = error;
    this.error_description = error_description;
  }
}
