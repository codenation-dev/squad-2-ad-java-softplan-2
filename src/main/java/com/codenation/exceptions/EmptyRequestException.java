package com.codenation.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmptyRequestException extends Exception {
  private String error = "resquisicao_nula";
  private String error_description = "A requisicao esta vazia ou contem dados nulos";
}