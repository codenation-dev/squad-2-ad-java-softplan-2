package com.codenation.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserNotNullException extends Exception {

  private String error;
  private String error_description;

  public UserNotNullException(String error, String error_description) {
    this.error = error;
    this.error_description = error_description;
  }
}
