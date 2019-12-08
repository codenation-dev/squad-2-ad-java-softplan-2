package com.codenation.resource;

import com.codenation.dto.UserDTO;
import com.codenation.entity.Role;
import com.codenation.entity.User;
import com.codenation.repository.RoleRepository;
import com.codenation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserResource {


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
  public ResponseEntity<HttpEntity> create(@RequestBody @Valid User user){
    Role role = roleRepository.findByName("USER");
    user.setRoles(Collections.singletonList(role));
    userService.save(user);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<HttpEntity> alterRole(@PathVariable Long id, @RequestBody String role){
    Optional<User> userOptional = userService.findById(id);
    Role roleResult = roleRepository.findByName(role);

    if(userOptional.isPresent() && roleResult != null){
      User user = userOptional.get();
      user.setRoles(Collections.singletonList(roleResult));
      userService.save(user);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.badRequest().build();
  }

}
