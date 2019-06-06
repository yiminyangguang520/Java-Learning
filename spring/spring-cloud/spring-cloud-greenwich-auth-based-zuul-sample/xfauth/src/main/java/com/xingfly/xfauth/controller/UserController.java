package com.xingfly.xfauth.controller;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by SuperS on 2017/9/25.
 *
 * @author SuperS
 */
@RestController
public class UserController {

  @GetMapping("/user")
  public Principal user(Principal user) {
    return user;
  }
}
