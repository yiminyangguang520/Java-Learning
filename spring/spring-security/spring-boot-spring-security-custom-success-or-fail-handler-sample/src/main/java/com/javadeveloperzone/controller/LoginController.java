package com.javadeveloperzone.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Lenovo
 * @date 19-07-2017
 */
@Controller
public class LoginController {


  @RequestMapping(value = "/login")
  public String springBootHello() {
    return "login";
  }

  @RequestMapping(value = "/loginSuccess")
  public String loginSuccess(HttpServletRequest request) {
    Integer integer = (Integer) request.getSession().getAttribute("hitCounter");
    if (integer == null) {
      integer = new Integer(0);
      integer++;
      request.getSession().setAttribute("hitCounter", integer);
    } else {
      integer++;
      request.getSession().setAttribute("hitCounter", integer);
    }
    return "welcome";
  }

  @RequestMapping(value = "/loginFailed")
  public String loginFailed() {
    return "loginFailed";
  }


}
