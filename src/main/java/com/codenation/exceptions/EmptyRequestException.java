package com.codenation.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmptyRequestException extends Exception {
  private String error = "resquisicao_nula";
  private String error_description = "A requisicao esta vazia ou contem dados invalidos";

  public EmptyRequestException(
          String error, String error_description) {
    this.error = error;
    this.error_description = error_description;
  }
}