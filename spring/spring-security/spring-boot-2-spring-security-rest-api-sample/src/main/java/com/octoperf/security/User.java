package com.octoperf.security;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Builder;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author litz-a
 */
@Value
@Builder
public class User implements UserDetails {

  private static final long serialVersionUID = 2396654715019746670L;

  String id;
  String username;
  String password;

  @JsonCreator
  User(@JsonProperty("id") final String id,
      @JsonProperty("username") final String username,
      @JsonProperty("password") final String password) {
    super();
    this.id = requireNonNull(id);
    this.username = requireNonNull(username);
    this.password = requireNonNull(password);
  }

  @JsonIgnore
  @Override
  public Collection<GrantedAuthority> getAuthorities() {
    return new ArrayList<>();
  }

  @JsonIgnore
  @Override
  public String getPassword() {
    return password;
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

  @Override
  public boolean isEnabled() {
    return true;
  }

}
