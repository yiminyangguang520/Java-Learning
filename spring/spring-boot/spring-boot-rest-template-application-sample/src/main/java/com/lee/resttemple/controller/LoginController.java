package com.lee.resttemple.controller;

import com.lee.resttemple.model.User;
import com.lee.resttemple.result.AccessToken;
import com.lee.resttemple.service.LoginService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RestController
public class LoginController {

  @Autowired
  private LoginService loginService;

  @PostMapping("/login")
  public AccessToken login(User user){
    return loginService.login(user);
  }

  @GetMapping("/validate")
  public Boolean validate(String account) throws IOException {
    return loginService.validate(account);
  }

  @PostMapping("/validate/{token}")
  public Boolean validateToken(@PathVariable String token) throws IOException {
    return loginService.validateToken(token);
  }
}
