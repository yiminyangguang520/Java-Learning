package com.gcr.sso.sample.controller;

import com.glodon.sso.core.constant.Constant;
import com.glodon.sso.core.entity.SsoResponse;
import com.glodon.sso.core.user.SsoUser;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xuxueli 2017-08-01 21:39:47
 */
@Controller
public class IndexController {

  @RequestMapping("/")
  public String index(Model model, HttpServletRequest request) {
    SsoUser ssoUser = (SsoUser) request.getAttribute(Constant.SSO_USER);
    model.addAttribute("ssoUser", ssoUser);
    return "index";
  }

  @RequestMapping("/json")
  @ResponseBody
  public SsoResponse<SsoUser> json(Model model, HttpServletRequest request) {
    SsoUser ssoUser = (SsoUser) request.getAttribute(Constant.SSO_USER);
    return new SsoResponse<>(ssoUser);
  }

}