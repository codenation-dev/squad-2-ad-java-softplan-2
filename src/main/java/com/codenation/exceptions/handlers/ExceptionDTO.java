package com.codenation.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExceptionDTO {
  private String error;
  private String error_description;

  protected ExceptionDTO() {}
}
