package com.us.example.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


/**
 * @author min
 */
@Getter
@Setter
@AllArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {

  private String url;
  private String method;

  @Override
  public String getAuthority() {
    return this.url + ";" + this.method;
  }
}