package com.room.controller;

import com.room.entity.StatusMessage;
import com.room.service.IUserService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 * 用户数据控制器
 * Created by Doublestar on 2017/11/24 11:16).
 */
@RestController
@RequestMapping(value = "/post")
public class UserController {

  @Autowired
  private IUserService userService;

  /**
   * 用户登录功能
   * @param session
   * @param name
   * @param password
   * @param authcode
   * @return
   */
  @RequestMapping(value = "/login.do", method = RequestMethod.POST)
  public StatusMessage search(HttpSession session, String name, String password, String authcode) {
    return userService.userLogin(session, name, password, authcode);
  }

  /**
   * 用户注册模块
   * @param name
   * @param password
   * @param repassword
   * @return
   */
  @RequestMapping(value = "/register.do", method = RequestMethod.POST)
  public StatusMessage register(String name, String password, String repassword) {
    return userService.userResistor(name, password, repassword);
  }

  /**
   * 用户忘记密码模块
   * @param name
   * @param address
   * @return
   */
  @RequestMapping(value = "forgetpassword.do", method = RequestMethod.POST)
  public StatusMessage forgetpassword(String name, String address) {
    return userService.forgetPassword(name, address);
  }

}
