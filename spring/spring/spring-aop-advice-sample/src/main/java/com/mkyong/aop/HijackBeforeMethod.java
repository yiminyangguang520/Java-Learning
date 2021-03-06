package com.mkyong.aop;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * @author min
 */
public class HijackBeforeMethod implements MethodBeforeAdvice {

  /**
   *
   * @param method
   * @param args
   * @param target
   * @throws Throwable
   */
  public void before(Method method, Object[] args, Object target) throws Throwable {
    System.out.println("HijackBeforeMethod : Before method hijacked!");
  }
}