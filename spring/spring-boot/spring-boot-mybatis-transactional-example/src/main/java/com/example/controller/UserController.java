package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
@RequestMapping(value = "/api")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping(value = "/user/{name}")
  public User getUser(@PathVariable String name) {
    return userService.findByUserName(name);
  }

}
