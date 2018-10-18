package com.javasampleapproach.springsecurity.authenticationprovider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author litz-a
 */
@Controller
public class WebController {

  @RequestMapping(value = {"/"})
  public String home() {
    return "home";
  }

  @RequestMapping(value = {"/user"})
  public String welcome() {
    return "user";
  }

  @RequestMapping(value = "/admin")
  public String admin() {
    return "admin";
  }

  @RequestMapping(value = {"/login"})
  public String login() {
    return "login";
  }

  @RequestMapping(value = {"/logoutsuccessful"})
  public String logoutsuccessful() {
    return "logoutsuccessful";
  }

  @RequestMapping(value = "/403")
  public String Error403() {
    return "403";
  }
}
