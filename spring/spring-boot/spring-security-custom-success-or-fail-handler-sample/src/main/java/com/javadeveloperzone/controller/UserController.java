package com.javadeveloperzone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Lenovo
 * @date 24-12-2017
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

  @RequestMapping(value = "/home")
  public String home() {
    return "home";
  }
}
