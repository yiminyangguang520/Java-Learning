package com.glodon.sso.server.controller;

import com.glodon.sso.core.constant.Constant;
import com.glodon.sso.core.exception.GcrSsoException;
import com.glodon.sso.core.user.SsoUser;
import com.glodon.sso.core.util.SsoLoginHelper;
import com.glodon.sso.server.constant.Domain;
import com.glodon.sso.server.service.UserService;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * sso server (for web)
 *
 * @author litz-a
 */
@Controller
public class IndexController {

  @Autowired
  private UserService userService;

  @RequestMapping("/")
  public String index(Model model, HttpServletRequest request) {
    SsoUser ssoUser = SsoLoginHelper.loginCheck(request);
    if (ssoUser == null) {
      return "redirect:/login";
    } else {
      model.addAttribute("xxlUser", ssoUser);
      return "index";
    }
  }

  /**
   * Login page
   */
  @RequestMapping(Constant.SSO_LOGIN)
  public String login(Model model, HttpServletRequest request) {
    SsoUser ssoUser = SsoLoginHelper.loginCheck(request);
    if (ssoUser != null) {
      String redirectUrl = request.getParameter(Constant.REDIRECT_URL);
      if (StringUtils.isNotBlank(redirectUrl)) {
        String sessionId = SsoLoginHelper.getSessionIdByCookie(request);
        String redirectUrlFinal = redirectUrl + "?" + Constant.SSO_SESSIONID + "=" + sessionId;
        return "redirect:" + redirectUrlFinal;
      } else {
        return "redirect:/";
      }
    }

    model.addAttribute("errorMsg", request.getParameter("errorMsg"));
    model.addAttribute(Constant.REDIRECT_URL, request.getParameter(Constant.REDIRECT_URL));
    return "login";
  }

  /**
   * Login
   */
  @RequestMapping("/doLogin")
  public String doLogin(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
      String username, String password) {
    String errorMsg = StringUtils.EMPTY;
    try {
      if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
        throw new GcrSsoException("用户名或密码为空");
      }

      String result = userService.domainAccountAuthenticate(username, password);
      if (!StringUtils.equals(result, Domain.DOMAIN_ACCOUNT_AUTHENTICATE_RESULT_PASS)) {
        throw new GcrSsoException("域账户认证失败");
      }
    } catch (Exception e) {
      errorMsg = e.getMessage();
    }

    if (StringUtils.isNotEmpty(errorMsg)) {
      redirectAttributes.addAttribute("errorMsg", errorMsg);
      redirectAttributes.addAttribute(Constant.REDIRECT_URL, request.getParameter(Constant.REDIRECT_URL));
      return "redirect:/login";
    }

    // login success
    SsoUser ssoUser = new SsoUser();
    ssoUser.setUsername(username);
    String sessionId = UUID.randomUUID().toString();
    SsoLoginHelper.login(response, sessionId, ssoUser);

    // success redirect
    String redirectUrl = request.getParameter(Constant.REDIRECT_URL);
    if (StringUtils.isNotBlank(redirectUrl)) {
      String redirectUrlFinal = redirectUrl + "?" + Constant.SSO_SESSIONID + "=" + sessionId;
      return "redirect:" + redirectUrl;
    } else {
      return "redirect:/";
    }
  }

  @RequestMapping(Constant.SSO_LOGOUT)
  public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
    SsoLoginHelper.logout(request, response);
    redirectAttributes.addAttribute(Constant.REDIRECT_URL, request.getParameter(Constant.REDIRECT_URL));
    return "redirect:/login";
  }
}