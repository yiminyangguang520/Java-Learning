package org.shiro.security.common.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.shiro.common.exception.ShiroException;
import org.shiro.common.utils.Result;
import org.shiro.security.common.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:45:34
 * 类说明：异常处理器
 */
@RestControllerAdvice
public class SSExceptionHandler {

  private Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * 处理自定义异常
   */
  @ExceptionHandler(ShiroException.class)
  public Result handleRRException(ShiroException e) {
    Result r = new Result();
    r.put("code", e.getCode());
    r.put("msg", e.getMessage());
    return r;
  }

  @ExceptionHandler(DuplicateKeyException.class)
  public Result handleDuplicateKeyException(DuplicateKeyException e) {
    logger.error(e.getMessage(), e);
    return Result.error(Constant.NOWEXITS);
  }

  @ExceptionHandler(AuthorizationException.class)
  public Result handleAuthorizationException(AuthorizationException e) {
    logger.error(e.getMessage(), e);
    return Result.error(Constant.NOACCESS);
  }

  @ExceptionHandler(Exception.class)
  public Result handleException(Exception e) {
    logger.error(e.getMessage(), e);
    return Result.error();
  }
}
