package com.jcombat.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author min
 */
@Controller
public class DemoController {

  @RequestMapping("mathematician")
  public String getMathematicianPage(ModelMap model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    model.addAttribute("username", username);
    return "/mathematician";
  }

  @RequestMapping("chemist")
  public String getChemistPage(ModelMap model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    model.addAttribute("username", username);
    return "/chemist";
  }

  @RequestMapping("403page")
  public String ge403denied() {
    return "/unauthorized";
  }
}