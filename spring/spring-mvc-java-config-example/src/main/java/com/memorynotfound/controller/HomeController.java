package com.memorynotfound.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author litz-a
 */
@Controller
@RequestMapping("/")
public class HomeController {

  @RequestMapping(method = RequestMethod.GET)
  public String index(ModelMap model) {
    model.addAttribute("message", "Spring MVC Java Config Example");
    return "index";
  }

}
