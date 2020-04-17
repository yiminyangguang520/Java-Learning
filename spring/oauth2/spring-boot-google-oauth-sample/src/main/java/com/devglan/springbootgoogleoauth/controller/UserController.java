package com.devglan.springbootgoogleoauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author min
 */
@Controller
public class UserController {

  @GetMapping("/home")
  public String home() {
    return "home";
  }
}
