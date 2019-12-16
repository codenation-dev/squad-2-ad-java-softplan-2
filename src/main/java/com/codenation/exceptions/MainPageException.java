package com.codenation.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MainPageException extends Exception {

  private String error = "bem_vindo";
  private String error_description = "Para comecar, visite nosso repositorio no"+
          "GitHub https://github.com/codenation-dev/squad-2-ad-java-softplan-2 ou confira a documentacao em /swagger-ui.html";

  public MainPageException(String error, String error_description) {
    this.error = error;
    this.error_description = error_description;
  }
}
