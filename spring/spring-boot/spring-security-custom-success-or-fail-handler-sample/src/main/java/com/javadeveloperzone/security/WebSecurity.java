package com.javadeveloperzone.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lenovo
 * @date 17-12-2017
 */
@Component
public class WebSecurity {

  public boolean checkUserId(UsernamePasswordAuthenticationToken authentication, Integer id) {
    System.out.println("home");
    return false;
  }
}
