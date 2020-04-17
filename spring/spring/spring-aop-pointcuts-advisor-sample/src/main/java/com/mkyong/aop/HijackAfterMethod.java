package com.mkyong.aop;

import java.lang.reflect.Method;
import org.springframework.aop.AfterReturningAdvice;

/**
 * @author min
 */
public class HijackAfterMethod implements AfterReturningAdvice {

  /**
   * after a method is invoke
   * @param returnValue
   * @param method
   * @param args
   * @param target
   * @throws Throwable
   */
  public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
    System.out.println("HijackAfterMethod : After method hijacked!");
  }

}
