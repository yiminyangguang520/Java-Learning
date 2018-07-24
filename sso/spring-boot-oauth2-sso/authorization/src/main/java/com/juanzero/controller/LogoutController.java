package com.juanzero.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author jjmendoza
 * @date 14/7/2017
 */
@Controller
public class LogoutController {

  @RequestMapping("/exit")
  public void exit(HttpServletRequest request, HttpServletResponse response) {
    new SecurityContextLogoutHandler().logout(request, null, null);
    try {
      response.sendRedirect(request.getHeader("referer"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
