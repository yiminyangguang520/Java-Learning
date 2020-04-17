package com.jwt.config;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author min
 */
public class CustomAuthToken extends UsernamePasswordAuthenticationToken {

  private static final long serialVersionUID = 1L;

  public CustomAuthToken(Object principal, Object credentials) {
    super(principal, credentials);
  }

  public CustomAuthToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
    super(principal, credentials, authorities);
  }
}