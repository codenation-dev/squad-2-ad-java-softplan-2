package com.codenation.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InvalidLogException extends Exception {
  private String error = "log_invalido";
  private String error_description = "Os dados do log informado sao invalidos, por favor, verifique";

  public InvalidLogException(
          String error, String error_description) {
    this.error = error;
    this.error_description = error_description;
  }
}

