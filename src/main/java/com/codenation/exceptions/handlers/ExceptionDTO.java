package com.codenation.exceptions.handlers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionDTO {
  private String error;
  private String error_description;

  public ExceptionDTO() {}
}
