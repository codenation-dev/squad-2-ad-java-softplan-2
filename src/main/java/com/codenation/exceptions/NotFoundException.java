package com.codenation.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotFoundException extends Exception {
  private String error = "pagina_nao_encontrada";
  private String error_description = "O caminho pode ter sido excluído ou estar temporariamente indisponivel. Acesse /swagger-ui.html para documentacao";

  public NotFoundException(
          String error, String error_description) {
    this.error = error;
    this.error_description = error_description;
  }
}
