package com.codenation.service;

import com.codenation.dto.UserDTO;
import com.codenation.entity.User;
import com.codenation.exceptions.UserNotFoundException;
import com.codenation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<UserDTO> findAll() {
    List<UserDTO> resultSet = new ArrayList<>();
    userRepository.findAll().forEach(user -> {
      resultSet.add(new UserDTO(user));
    });

    return resultSet;
  }

  public Optional<User> findByEmail(String email){
    return userRepository.findByEmail(email);
  }

  public Optional<UserDTO> findById (Long id) throws UserNotFoundException {
    Optional<User> userOptional =  userRepository.findById(id);
    User user = userOptional.orElseThrow(UserNotFoundException::new);

    return Optional.of(new UserDTO(user));
  }

  public Optional<User> findUser (Long id) throws UserNotFoundException {
    return userRepository.findById(id);
  }

  public void save(User user){
    userRepository.save(user);
  }
}
