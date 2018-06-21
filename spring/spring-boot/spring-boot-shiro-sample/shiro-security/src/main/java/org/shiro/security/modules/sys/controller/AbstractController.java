package org.shiro.security.modules.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.shiro.common.utils.Result;
import org.shiro.security.modules.sys.entity.SysUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午11:08:06 类说明：Controller公共组件
 */
public abstract class AbstractController {

  protected Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * 登录认证异常
   */
  @ExceptionHandler({UnauthenticatedException.class, AuthenticationException.class})
  public Result authenticationException(HttpServletRequest request, HttpServletResponse response) {
    return Result.error("用户未登陆");
  }

  /**
   * 权限异常
   */
  @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
  public Result authorizationException(HttpServletRequest request, HttpServletResponse response) {
    return Result.error("无权限，请于管理员联系");
  }

  protected SysUserEntity getUser() {
    return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
  }

  protected Long getUserId() {
    return getUser().getUserId();
  }

  protected Long getDeptId() {
    return getUser().getDeptId();
  }

}
