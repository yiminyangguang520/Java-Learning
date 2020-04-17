package com.us.example.controller;

import com.us.example.domain.User;
import com.us.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author min
 */
@RestController
@RequestMapping("/users")
public class HomeController {

  @Autowired
  UserService userService;

  @RequestMapping(method = RequestMethod.GET)
  public String getUsers() {
    return "getUsers";
  }

  @Secured({"ROLE_ADMIN", "ROLE_USER"})
  @RequestMapping(method = RequestMethod.POST)
  public Object save(@RequestBody User user) {
    return userService.create(user);
  }


  @Secured("ROLE_ADMIN")
  @RequestMapping(method = RequestMethod.PUT)
  public String update() {
    return "updateUser";
  }


  @Secured("ROLE_ADMIN")
  @RequestMapping(method = RequestMethod.DELETE)
  public String delete() {
    return "deleteUser";
  }


}

