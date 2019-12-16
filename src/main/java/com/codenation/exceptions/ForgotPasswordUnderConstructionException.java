package com.codenation.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ForgotPasswordUnderConstructionException extends Exception {

  private String error = "implementacao_em_andamento";
  private String error_description = "Esta funcionalidade ainda esta sob construcao. Caso tenha problemas, visite o nosso repositorio no Github https://github.com/codenation-dev/squad-2-ad-java-softplan-2";

  public ForgotPasswordUnderConstructionException(String error, String error_description) {
    this.error = error;
    this.error_description = error_description;
  }
}
