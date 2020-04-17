package com.javacodegeeks.snippets.enterprise.aop;

import java.util.Arrays;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author min
 */
public class DoAroundMethod2 implements MethodInterceptor {

  @Override
  public Object invoke(MethodInvocation methodInvocation) throws Throwable {

    System.out.println("****SPRING AOP**** DoAroundMethod 2: Method name : " + methodInvocation.getMethod().getName());

    System.out.println("****SPRING AOP**** DoAroundMethod 2: Method arguments : " + Arrays.toString(methodInvocation.getArguments()));
    // same with MethodBeforeAdvice
    System.out.println("****SPRING AOP**** DoAroundMethod 2: Before method executing!");

    try {
      // proceed to original method call
      Object result = methodInvocation.proceed();
      // same with AfterReturningAdvice
      System.out.println("****SPRING AOP**** DoAroundMethod 2: After method executing!");
      return result;
    } catch (IllegalArgumentException e) {
      // same with ThrowsAdvice
      System.out.println("****SPRING AOP**** DoAroundMethod 2: When method throws Exception!");
      throw e;
    }
  }

}
