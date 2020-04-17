package com.room.controller;

import com.room.entity.StatusMessage;
import com.room.service.IUserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author min
 * Created by Doublestar on 2017/11/28 20:53).
 */
@Controller
@RequestMapping("/home")
public class HomeController {

  @Autowired
  private IUserService userService;

  @RequestMapping("/index")
  public String index() {
    return "chat";
  }

  @RequestMapping(value = "/admin", method = RequestMethod.GET)
  public String mq() throws ServletException, IOException {
    return "mq";
  }

  /**
   * 用户注销模块非Ajax
   * @param session
   * @return
   */
  @RequestMapping("/logout")
  public String logout(HttpSession session) {
    userService.userLogout(session);
    return "redirect:/login";
  }

  /**
   * 用户注销模块Ajax
   * @param session
   * @param name
   * @return
   */
  @RequestMapping(value = "/post/logout.do", method = RequestMethod.POST)
  public StatusMessage logoutAjax(HttpSession session, String name) {
    return userService.userLogoutAjax(session, name);
  }

}
