package com.us.example.controller;

import com.us.example.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
public class LoginController {

  /**
   * 用户名密码是用base64 加密 原文为 admin:admin 即 用户名:密码  内容是放在request.getHeader 的 "authorization" 中
   * @param loginedUser
   * @param logout
   * @return
   */
  @RequestMapping(value = "/login")
  public Object login(@AuthenticationPrincipal User loginedUser,
      @RequestParam(name = "logout", required = false) String logout) {
    if (logout != null) {
      return null;
    }
    if (loginedUser != null) {
      return loginedUser;
    }
    return null;
  }
}
