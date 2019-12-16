package com.codenation.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAlreadyExistsException extends Exception {

  private String error = "usuario_existente";
  private String error_description = "Este e-mail ja esta cadastrado";

  public UserAlreadyExistsException(String error, String error_description) {
    this.error = error;
    this.error_description = error_description;
  }
}
