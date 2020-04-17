package com.example.auth.security;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
public class AuthUser {

  @GetMapping("/oauth/user")
  public Principal user(Principal principal) {
    return principal;
  }

}
