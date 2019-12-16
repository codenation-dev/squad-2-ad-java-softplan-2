package com.codenation.resource;

import com.codenation.exceptions.NotFoundException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorPageResource implements ErrorController {

  @GetMapping("/error")
  public String handleError() throws NotFoundException {
    throw new NotFoundException();
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }
}
