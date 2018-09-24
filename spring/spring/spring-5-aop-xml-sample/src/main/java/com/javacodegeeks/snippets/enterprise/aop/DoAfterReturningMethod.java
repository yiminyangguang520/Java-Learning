package com.javacodegeeks.snippets.enterprise.aop;

import java.lang.reflect.Method;
import org.springframework.aop.AfterReturningAdvice;

/**
 * @author litz-a
 */
public class DoAfterReturningMethod implements AfterReturningAdvice {

  @Override
  public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
    System.out.println("****SPRING AOP**** DoAfterReturningMethod : Executing after method return!");
  }

}
