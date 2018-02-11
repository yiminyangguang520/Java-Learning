package com.my.blog.website.controller.admin;

import com.my.blog.website.constant.WebConst;
import com.my.blog.website.controller.BaseController;
import com.my.blog.website.dto.LogActions;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.modal.bo.RestResponseBo;
import com.my.blog.website.modal.vo.UserVo;
import com.my.blog.website.service.ILogService;
import com.my.blog.website.service.IUserService;
import com.my.blog.website.utils.Commons;
import com.my.blog.website.utils.TaleUtils;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户后台登录/登出
 * @author litz-a
 * Created by BlueT on 2017/3/11.
 */
@Controller
@RequestMapping("/admin")
@Transactional(rollbackFor = TipException.class)
public class AuthController extends BaseController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

  @Resource
  private IUserService usersService;

  @Resource
  private ILogService logService;

  @GetMapping(value = "/login")
  public String login() {
    return "admin/login";
  }

  /**
   * 管理后台登录
   */
  @PostMapping(value = "login")
  @ResponseBody
  public RestResponseBo doLogin(@RequestParam String username,
      @RequestParam String password,
      @RequestParam(required = false) String remeber_me,
      HttpServletRequest request,
      HttpServletResponse response) {

    Integer error_count = cache.get("login_error_count");
    try {
      UserVo user = usersService.login(username, password);
      request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
      if (StringUtils.isNotBlank(remeber_me)) {
        TaleUtils.setCookie(response, user.getUid());
      }
      logService.insertLog(LogActions.LOGIN.getAction(), null, request.getRemoteAddr(), user.getUid());
    } catch (Exception e) {
      error_count = null == error_count ? 1 : error_count + 1;
      if (error_count > 3) {
        return RestResponseBo.fail("您输入密码已经错误超过3次，请10分钟后尝试");
      }
      cache.set("login_error_count", error_count, 10 * 60);
      String msg = "登录失败";
      if (e instanceof TipException) {
        msg = e.getMessage();
      } else {
        LOGGER.error(msg, e);
      }
      return RestResponseBo.fail(msg);
    }
    return RestResponseBo.ok();
  }

  /**
   * 注销
   */
  @RequestMapping("/logout")
  public void logout(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
    session.removeAttribute(WebConst.LOGIN_SESSION_KEY);
    Cookie cookie = new Cookie(WebConst.USER_IN_COOKIE, "");
    cookie.setMaxAge(0);
    response.addCookie(cookie);
    try {
      ////response.sendRedirect(Commons.site_url());
      response.sendRedirect(Commons.site_login());
    } catch (IOException e) {
      e.printStackTrace();
      LOGGER.error("注销失败", e);
    }
  }
}
