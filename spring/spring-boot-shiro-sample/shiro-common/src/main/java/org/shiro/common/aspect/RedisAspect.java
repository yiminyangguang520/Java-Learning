package org.shiro.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.shiro.common.exception.ShiroException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午9:34:37
 * 类说明：Redis切面处理类
 */
@Aspect
@Component
public class RedisAspect {

  private Logger logger = LoggerFactory.getLogger(getClass());
  //是否开启redis缓存  true开启   false关闭
  @Value("${shiro.redis.open: false}")
  private boolean open;

  @Around("execution(* org.shiro.common.utils.RedisUtils.*(..))")
  public Object around(ProceedingJoinPoint point) throws Throwable {
    Object result = null;
    if (open) {
      try {
        result = point.proceed();
      } catch (Exception e) {
        logger.error("redis error", e);
        throw new ShiroException("Redis服务异常");
      }
    }
    return result;
  }
}
