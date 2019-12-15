package com.codenation.resource;

import com.codenation.dto.UserDTO;
import com.codenation.entity.Role;
import com.codenation.entity.User;
import com.codenation.repository.RoleRepository;
import com.codenation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/user")
public class UserResource {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserService userService;

  @Autowired
  private RoleRepository roleRepository;

  @GetMapping()
  public List<UserDTO> findAll(){
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public UserDTO findById(Long id){
    Optional<User> userOptional = userService.findById(id);

    if(userOptional.isPresent()) {
      User user = userOptional.get();

      return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getAuthorities());
    }

    return null;
  }

  @PostMapping
  public ResponseEntity<HttpEntity> create(@RequestBody @Valid UserDTO userDTO){
    User result = new User();

    Optional<Role> roleOptional = roleRepository.findByName("USER");

    if(roleOptional.isPresent()){
      Role role = roleOptional.get();
      result.setRoles(Collections.singletonList(role));
    }

    result.setName(userDTO.getName());
    result.setEmail(userDTO.getEmail());
    result.setPassword(passwordEncoder.encode(userDTO.getPassword()));

    userService.save(result);

    return ResponseEntity.ok().build(); //create
  }

  @PatchMapping("/{id}")
  public ResponseEntity<HttpEntity> alterRole(@PathVariable Long id, @RequestBody String role){
    Role roleResult;
    Optional<User> userOptional = userService.findById(id);
    Optional<Role> roleOptional = roleRepository.findByName(role);

    if(userOptional.isPresent() && roleOptional.isPresent()){
      User user = userOptional.get();
      roleResult = roleOptional.get();

      user.setRoles(Collections.singletonList(roleResult));

      userService.save(user);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.badRequest().build();
  }

}
