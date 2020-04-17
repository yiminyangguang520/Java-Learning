package com.room.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


/**
 * @author min
 * Created by Doublestar on 2017/12/13 9:53).
 */
@Component
@Aspect
public class Log4jAspect {
//    @Pointcut("execution(* com.test.service.*.*(..))")

  @Before("execution(* com.room.service.impl.*.*(..))")
  public void beforeAction(JoinPoint joinpoint) {
    Logger logger = Logger.getLogger(joinpoint.getTarget().getClass().getName());
    String logStr = joinpoint.getTarget().getClass().getName() + "类的"
        + joinpoint.getSignature().getName() + "方法执行开始******Start******";
    System.out.println(logStr);
    logger.warn(logStr);
  }

  @After("execution(* com.room.service.impl.*.*(..))")
  public void afterAction(JoinPoint joinpoint) {
    Logger logger = Logger.getLogger(joinpoint.getTarget().getClass().getName());
    String logStr = joinpoint.getTarget().getClass().getName() + "类的"
        + joinpoint.getSignature().getName() + "方法执行结束******End******";
    System.out.println(logStr);
    logger.warn(logStr);
  }
}
