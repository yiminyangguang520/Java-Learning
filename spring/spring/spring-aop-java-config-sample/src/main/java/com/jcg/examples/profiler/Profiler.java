package com.jcg.examples.profiler;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author min
 */
@Aspect
@Component
public class Profiler {

  @Before("execution(* com.jcg.examples.bo.BusinessTargetObject.sayHello(..))")
  public void logBeforeTxn(JoinPoint joinpoint) {
    System.out.println("Beginning execution for " + joinpoint.getSignature().getName());
  }

  @After("execution(* com.jcg.examples.bo.BusinessTargetObject.sayHello(..))")
  public void logAfterTxn(JoinPoint joinpoint) {
    System.out.println("Execution completed for " + joinpoint.getSignature().getName());
  }

  @Around("execution(* com.jcg.examples.bo.BusinessTargetObject.merryGoAround(..))")
  public void logAroundTxn(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    System.out.println("Beginning execution for " + proceedingJoinPoint.getSignature().getName());
    proceedingJoinPoint.proceed();
    System.out.println("Execution completed for " + proceedingJoinPoint.getSignature().getName());
  }

  @AfterReturning(pointcut = "execution(* com.jcg.examples.bo.BusinessTargetObject.performTransaction(..))", returning = "retVal")
  public void logResultsAfterTxn(JoinPoint joinpoint, Object retVal) {
    System.out.println("Execution completed for " + joinpoint.getSignature().getName());
    System.out.println("Value being returned is " + retVal);
  }

  @AfterThrowing(pointcut = "execution(* com.jcg.examples.bo.BusinessTargetObject.throwException(..))", throwing = "exception")
  public void logResultsAfterError(JoinPoint joinpoint, Throwable exception) {
    System.out.println("Execution completed for " + joinpoint.getSignature().getName());
    System.out.println("Error in Join Point due to : " + exception.getMessage());
    System.out.println("Advice complete");
  }
}
