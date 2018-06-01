package org.shiro.security.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.shiro.common.utils.Result;
import org.shiro.security.modules.sys.dao.SysUserDao;
import org.shiro.security.modules.sys.shiro.ShiroUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午11:12:15 类说明：登录相关
 */
@Controller
@Api(value = "登陆信息")
public class SysLoginController {

  @Resource
  private SysUserDao sysUserDao;

  /**
   * 登录
   */
  @ResponseBody
  @PostMapping(value = "/sys/login")
  @ApiOperation(value = "登陆", httpMethod = "POST", produces = "application/json", response = Result.class)
  public Result login(String username, String password) {
    Subject subject = ShiroUtils.getSubject();
    try {
      UsernamePasswordToken token = new UsernamePasswordToken(username, password);
      subject.login(token);
    } catch (UnknownAccountException e) {
      return Result.error(e.getMessage());
    } catch (IncorrectCredentialsException e) {
      return Result.error("账号或密码不正确");
    } catch (LockedAccountException e) {
      return Result.error("账号已被锁定,请联系管理员");
    } catch (AuthenticationException e) {
      return Result.error("账户验证失败");
    }
    return Result.ok().put("session", subject.getSession().getId());
  }

  /**
   * 退出
   */
  @GetMapping(value = "logout")
  @ResponseBody
  @ApiOperation(value = "注销", httpMethod = "GET", produces = "application/json", response = Result.class)
  public Result logout() {
    try {
      ShiroUtils.logout();
    } catch (Exception e) {
      Result.error("注销失败");
    }
    return Result.ok("注销成功");

  }

}
