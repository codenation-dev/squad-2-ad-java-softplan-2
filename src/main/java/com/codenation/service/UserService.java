package com.codenation.service;

import com.codenation.dto.UserDTO;
import com.codenation.entity.User;
import com.codenation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    for(User U: userRepository.findAll()){
      resultSet.add(new UserDTO(U.getId(), U.getName(), U.getEmail(), U.getPassword(), U.getAuthorities()));
    }
    return resultSet;
  }

  public Optional<User> findById (Long id){
    return userRepository.findById(id);
  }

  public void save(User user){
    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    userRepository.save(user);
  }
}
