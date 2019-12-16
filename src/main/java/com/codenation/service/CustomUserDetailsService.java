package com.codenation.service;

import com.codenation.entity.Authority;
import com.codenation.entity.Role;
import com.codenation.entity.User;
import com.codenation.exceptions.RoleNotFoundException;
import com.codenation.exceptions.UserNotFoundException;
import com.codenation.repository.RoleRepository;
import com.codenation.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("userDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService service;

  @Autowired
  private MessageSource messages;

  @Autowired
  private RoleRepository roleRepository;

  @SneakyThrows
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{

    User user = userRepository.findByEmail(email).orElseThrow(() -> {throw new UsernameNotFoundException("Usuario nao encontrado");});

    return new org.springframework.security.core.userdetails.User(
            user.getEmail(), user.getPassword(), true, true, true,
            true, getAuthorities(user.getRoles()));
  }

  private Collection<? extends GrantedAuthority> getAuthorities(
          Collection<Role> roles) {

    return getGrantedAuthorities(getPrivileges(roles));
  }

  private List<String> getPrivileges(Collection<Role> roles) {

    List<String> privileges = new ArrayList<>();
    List<Authority> collection = new ArrayList<>();

    roles.forEach(item -> collection.addAll(item.getAuthorities()));

    collection.forEach(item -> privileges.add(item.getName()));

    return privileges;
  }

  private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String privilege : privileges) {
      authorities.add(new SimpleGrantedAuthority(privilege));
    }
    return authorities;
  }
}
