package cn.merryyou.soo.client.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
public class UserController {

  @GetMapping("/user")
  public Authentication user(Authentication user) {
    return user;
  }
}
