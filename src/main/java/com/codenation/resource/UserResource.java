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
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserResource {


  @Autowired
  private UserService userService;

  @GetMapping()
  public List<UserDTO> findAll(){
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public UserDTO findById(Long id){
    return userService.findById(id);
  }

  @PostMapping
  public ResponseEntity<HttpEntity> create(@RequestBody @Valid User user){
    userService.save(user);
    return ResponseEntity.ok().build();
  }
}
