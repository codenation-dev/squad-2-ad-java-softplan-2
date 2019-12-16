package com.codenation.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InvalidUserException extends Exception {
  private String error ="usuario_invalido";
  private String error_description = "Os dados de usuario sao invalidos, por favor, verifique";

  public InvalidUserException(
          String error, String error_description) {
    this.error = error;
    this.error_description = error_description;
  }
}
