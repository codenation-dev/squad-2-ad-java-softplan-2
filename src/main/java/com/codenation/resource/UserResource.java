package com.codenation.resource;

import com.codenation.dto.UserDTO;
import com.codenation.entity.Role;
import com.codenation.entity.User;
import com.codenation.exceptions.*;
import com.codenation.repository.RoleRepository;
import com.codenation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


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
  public ResponseEntity<HttpEntity> create(@RequestBody @Valid UserDTO userDTO) throws UserAlreadyExistsException, SignUpException {
    List<String> erros = new ArrayList<>();

    AtomicReference<Boolean> flag = new AtomicReference<>(false);
    userService.findByEmail(userDTO.getEmail()).ifPresent(
            item -> {
              flag.set(true);
            }
    );

    if(flag.get()) throw new UserAlreadyExistsException();

    User result = new User();

    roleRepository.findByNameIgnoreCase("USER").ifPresent(
            role -> {
              result.setRoles(Collections.singletonList(role));
            }
    );

    if(!userDTO.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"))
      erros.add("A senha deve possuir no minimo 8 caracteres, entre letras e numeros");
    if(userDTO.getName().length() < 3)
      erros.add("O nome deve possuir pelo menos 3 caracteres");

    if (!erros.isEmpty()) throw new SignUpException(erros);

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

  @PostMapping("/forgot_password")
  public ResponseEntity<HttpEntity> forgotPassword(@RequestParam Long id, HttpServletRequest req) throws ForgotPasswordUnderConstructionException {

    //Implementação da funcionalidade de recuperação de senha
    throw new ForgotPasswordUnderConstructionException();
  }

}
