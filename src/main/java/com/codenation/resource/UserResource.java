package com.codenation.resource;

import com.codenation.dto.UserDTO;
import com.codenation.entity.User;
import com.codenation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserResource {


  @Autowired
  private UserService userService;

  @GetMapping()
  public Iterable<User> findAll(Pageable pageable){
    return userService.findAll(pageable);
  }

  @PostMapping
  public ResponseEntity<HttpEntity> create(@RequestBody @Valid UserDTO userDTO){
    userService.save(userDTO);
    return ResponseEntity.ok().build();
  }
}
