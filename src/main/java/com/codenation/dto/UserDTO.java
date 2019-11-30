package com.codenation.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Null;
import java.util.Collection;

public class UserDTO implements UserDetails {

  @Null
  private long id;

  private String name;
  private String email;
  private String password;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return name;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public UserDTO setPassword(String password) {
    this.password = password;
    return this;
  }

  private int accessLevel;

  public String getName() {
    return name;
  }

  public UserDTO setName(String name) {
    this.name = name;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserDTO setEmail(String email) {
    this.email = email;
    return this;
  }

  public int getAccessLevel() {
    return accessLevel;
  }

  public UserDTO setAccessLevel(int accessLevel) {
    this.accessLevel = accessLevel;
    return this;
  }

  public UserDTO(String name, String email, String password, int accessLevel) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.accessLevel = accessLevel;
  }
}
