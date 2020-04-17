package com.us.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * implements UserDetails 用于登录时 @AuthenticationPrincipal 标签取值
 * @author min
 */
public class User implements UserDetails {

  private Integer id;

  private String username;

  @JsonIgnore
  private String password;

  private String rawPassword;

  @JsonIgnore
  private List<Role> roles;

  private List<? extends GrantedAuthority> authorities;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public String getRawPassword() {
    return rawPassword;
  }

  public void setRawPassword(String rawPassword) {
    this.rawPassword = rawPassword;
  }


  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }


  @JsonIgnore
  @Override
  public boolean isEnabled() {
    return true;
  }

  @JsonIgnore
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public void setGrantedAuthorities(List<? extends GrantedAuthority> authorities) {
    this.authorities = authorities;
  }

}
