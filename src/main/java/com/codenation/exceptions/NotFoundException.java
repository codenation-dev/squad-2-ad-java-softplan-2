package com.codenation.exceptions;

public class NotFoundException extends Exception {
  private static final String ERROR = "pagina_nao_encontrada";
  private static final String ERROR_DESCRIPTION= "O caminho pode ter sido excluído ou estar temporariamente indisponivel. Acesse /swagger-ui.html para documentacao";

  public static String getError() {
    return ERROR;
  }

  public static String getErrorDescription() {
    return ERROR_DESCRIPTION;
  }
}

