package com.javacodegeeks.snippets.enterprise.aop;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * @author litz-a
 */
public class DoBeforeMethod implements MethodBeforeAdvice {

  @Override
  public void before(Method method, Object[] args, Object target) throws Throwable {
    System.out.println("****SPRING AOP**** DoBeforeMethod : Executing before method!");
  }
}