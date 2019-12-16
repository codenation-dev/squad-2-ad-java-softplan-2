package com.codenation.resource;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorPageResource implements ErrorController {

  @RequestMapping("/error")
  public String handleError() {
    return "error";
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }
}
