package ns.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author litz-a
 */
@Aspect
@Slf4j
public class LogMethodExecutionTimeAspect {

  @Around("@annotation(LogMethodExecutionTime)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    final long start = System.currentTimeMillis();
    final Object proceed = joinPoint.proceed();
    final long executionTime = System.currentTimeMillis() - start;
    log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
    return proceed;
  }

}
