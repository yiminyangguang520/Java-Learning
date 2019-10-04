package com.lee.mybatis.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author bruce
 */
@Aspect
@Component
public class ReadOnlyDataSourceAspect implements Ordered {

  private static final Logger LOG = LoggerFactory.getLogger(ReadOnlyDataSourceAspect.class);

  @Around("@annotation(readOnlyDataSource)")
  public Object proceed(ProceedingJoinPoint proceedingJoinPoint, ReadOnlyDataSource readOnlyDataSource) throws Throwable {
    try {
      LOG.info("Switch to readonly datasource");
      DbContextHolder.setDbType(DbContextHolder.DbType.SLAVE);
      return proceedingJoinPoint.proceed();
    } finally {
      DbContextHolder.clearDbType();
      LOG.info("Restore to write datasource");
    }
  }

  @Override
  public int getOrder() {
    return 0;
  }
}
