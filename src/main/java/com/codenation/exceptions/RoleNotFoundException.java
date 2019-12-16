package com.codenation.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleNotFoundException extends Exception {
  private String error = "usuario_nao_encontrado";
  private String error_description = "O usuário nao foi encontrado. Pode ter sido movido ou excluido";

  public RoleNotFoundException(
          String error, String error_description) {
    this.error = error;
    this.error_description = error_description;
  }
}

