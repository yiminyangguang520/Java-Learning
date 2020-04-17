package com.boraji.tutorial.spring.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author min
 */
@Controller
public class MyController {

  @GetMapping("/")
  public String index(Model model) {

    // Get authenticated user name from SecurityContext
    SecurityContext context = SecurityContextHolder.getContext();

    model.addAttribute("message", "You are logged in as "
        + context.getAuthentication().getName());
    return "index";
  }
}
