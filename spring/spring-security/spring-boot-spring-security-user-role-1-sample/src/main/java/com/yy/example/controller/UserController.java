package com.yy.example.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RequestMapping(value = "/users")
@RestController
public class UserController {


  @GetMapping
  public Object list(HttpServletRequest request) {
    return "Get all User";
  }


  @GetMapping(value = "/{id}")
  public Object detail(@PathVariable Integer id) {
    return "Get a user";
  }

  @PostMapping
  public Object create(HttpServletRequest request) {
    return "POST a user";
  }

  @PutMapping
  public Object update(HttpServletRequest request) {
    return "PUT a user";
  }

}