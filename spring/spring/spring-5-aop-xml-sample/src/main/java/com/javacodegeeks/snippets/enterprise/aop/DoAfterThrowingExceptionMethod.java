package com.javacodegeeks.snippets.enterprise.aop;

import org.springframework.aop.ThrowsAdvice;

/**
 * @author litz-a
 */
public class DoAfterThrowingExceptionMethod implements ThrowsAdvice {

  public void afterThrowing(IllegalArgumentException e) throws Throwable {
    System.out.println("****SPRING AOP**** DoAfterThrowingExceptionMethod : Executing when method throws exception!");
  }
}
