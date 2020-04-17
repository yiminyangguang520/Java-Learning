package com.zhu.kaptcha.controller;

import com.zhu.kaptcha.util.CodeUtil;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
public class HelloWorld {

  @RequestMapping("/hello")
  public String hello(HttpServletRequest request) {
    if (!CodeUtil.checkVerifyCode(request)) {
      return "验证码有误！";
    } else {
      return "hello,world";
    }
  }
}
