package com.codenation.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserNotPermittedException extends Exception {

  private String error = "usuario_nao_permitido";
  private String error_description = "A solicitacao de alteracao de dados de usuario deve ser feita pelo proprio ou Administrador";

  public UserNotPermittedException(String error, String error_description) {
    this.error = error;
    this.error_description = error_description;
  }
}
