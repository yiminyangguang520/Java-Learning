package com.javasampleapproach.rememberme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author min
 */
@Controller
public class WebController {

  @RequestMapping(value = {"/"})
  public String home() {
    return "home";
  }

  @RequestMapping(value = {"/login"})
  public String login() {
    return "login";
  }
}