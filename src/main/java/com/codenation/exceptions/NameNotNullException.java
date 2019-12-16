package com.codenation.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NameNotNullException extends Exception {

  private String error = "nome_nulo_ou_curto";
  private String error_description = "O nome deve conter no minimo 5 caracteres";

  public NameNotNullException(String error, String error_description) {
    this.error = error;
    this.error_description = error_description;
  }
}
