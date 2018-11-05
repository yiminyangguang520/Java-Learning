package com.lee.authlogin.controller;

import com.lee.authlogin.service.UserService;
import com.lee.authlogin.valueobject.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping(value = "/user")
  public UserView getUserByName(@RequestParam("userName") String userName) {
    return userService.getUserByUserName(userName);
  }
}
