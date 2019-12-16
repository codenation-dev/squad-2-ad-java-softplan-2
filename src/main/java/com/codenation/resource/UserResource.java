package com.codenation.resource;

import com.codenation.dto.UserDTO;
import com.codenation.entity.Role;
import com.codenation.entity.User;
import com.codenation.exceptions.RoleNotFoundException;
import com.codenation.exceptions.UserNotFoundException;
import com.codenation.repository.RoleRepository;
import com.codenation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
  public UserDTO findById(@PathVariable Long id) throws UserNotFoundException {
    Optional<UserDTO> userOptional = userService.findById(id);

    if(!userOptional.isPresent()) throw new UserNotFoundException();

    return userOptional.get();
  }

  @PostMapping
  public ResponseEntity<HttpEntity> create(@RequestBody @Valid UserDTO userDTO){
    User result = new User();

    Optional<Role> roleOptional = roleRepository.findByNameIgnoreCase("USER");

    if(roleOptional.isPresent()){
      Role role = roleOptional.get();
      result.setRoles(Collections.singletonList(role));
    }

    result.setName(userDTO.getName());
    result.setEmail(userDTO.getEmail());
    result.setPassword(passwordEncoder.encode(userDTO.getPassword()));

    userService.save(result);

    return ResponseEntity.status(201).build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<HttpEntity> alterRole(@PathVariable Long id, @RequestBody @Valid Role role) throws UserNotFoundException, RoleNotFoundException {

    User user = userService.findUser(id)
            .orElseThrow(UserNotFoundException::new);

    Role roleResult = roleRepository.findByNameIgnoreCase(role.getName())
            .orElseThrow(RoleNotFoundException::new);

    user.setRoles(Collections.singletonList(roleResult));

    userService.save(user);
    return ResponseEntity.ok().build();
  }

}
