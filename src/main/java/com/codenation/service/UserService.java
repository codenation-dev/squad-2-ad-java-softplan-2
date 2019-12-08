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

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  public List<UserDTO> findAll() {
    List<UserDTO> resultSet = new ArrayList<>();
    for(User U: userRepository.findAll()){
      resultSet.add(new UserDTO(U.getName(), U.getEmail(), U.getPassword(), U.getAccessLevel()));
    }
    return resultSet;
  }

  public UserDTO findById (Long id){
    User user = userRepository.findById(id).get();
    return new UserDTO(user.getName(),user.getEmail(), user.getPassword(), user.getAccessLevel());
  }

  public void save(User user){
    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    userRepository.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(s);
    if (user == null) throw new UsernameNotFoundException(s);

    return new UserDTO(user.getName(), user.getEmail(), "{bcrypt}"+user.getPassword(), user.getAccessLevel());
  }
}
