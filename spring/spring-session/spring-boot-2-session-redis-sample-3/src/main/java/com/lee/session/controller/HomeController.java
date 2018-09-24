package com.lee.session.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author jitendra on 5/3/16.
 */
@Controller
public class HomeController {

  @RequestMapping("/setValue")
  public String setValue(@RequestParam(name = "key", required = false) String key,
      @RequestParam(name = "value", required = false) String value,
      HttpServletRequest request) {
    if (!ObjectUtils.isEmpty(key) && !ObjectUtils.isEmpty(value)) {
      request.getSession().setAttribute(key, value);
    }
    return "home";
  }
}