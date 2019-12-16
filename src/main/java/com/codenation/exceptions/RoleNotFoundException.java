package com.codenation.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleNotFoundException extends Exception {
  private String error = "role_nao_encontrada";
  private String error_description = "A role informada nao foi encontrada";

  public RoleNotFoundException(
          String error, String error_description) {
    this.error = error;
    this.error_description = error_description;
  }
}

