package com.codenation.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LogNotFoundException extends Exception {
  private String error = "log_nao_encontrado";
  private String error_description = "O log nao foi encontrado. Pode ter sido movido ou excluido";

  public LogNotFoundException(
          String error, String error_description) {
    this.error = error;
    this.error_description = error_description;
  }
}

