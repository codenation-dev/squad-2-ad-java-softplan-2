package com.codenation.resource;

import com.codenation.exceptions.MainPageException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ServerResource {

  @GetMapping
  public void mainPage() throws MainPageException {
    throw new MainPageException();
  }
}
