package com.memorynotfound.spring.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author min
 */
@Controller
public class HomeController {

  @GetMapping("/")
  public String greeting() {
    return "index";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }
}
