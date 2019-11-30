package com.codenation.service;

import com.codenation.dto.UserDTO;
import com.codenation.entity.User;
import com.codenation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  public Page<User> findAll(Pageable pageable) {return userRepository.findAll(pageable); }

  public User save(UserDTO userDTO){
    User user = new User(userDTO.getName(),userDTO.getEmail(),userDTO.getPassword(),userDTO.getAccessLevel());
    return userRepository.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    User user = userRepository.findByName(s);
    if (user == null) throw new UsernameNotFoundException(s);

    return new UserDTO(user.getName(), user.getEmail(), user.getPassword(), user.getAcessLevel());
  }
}
