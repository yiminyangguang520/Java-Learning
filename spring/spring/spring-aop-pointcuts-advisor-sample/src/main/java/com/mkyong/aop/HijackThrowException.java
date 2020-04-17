package com.mkyong.aop;

import org.springframework.aop.ThrowsAdvice;

/**
 * @author min
 */
public class HijackThrowException implements ThrowsAdvice {

  /**
   * afterThrowing
   * @param e
   * @throws Throwable
   */
  public void afterThrowing(IllegalArgumentException e) throws Throwable {
    System.out.println("HijackThrowException : Throw exception hijacked!");
  }

}
