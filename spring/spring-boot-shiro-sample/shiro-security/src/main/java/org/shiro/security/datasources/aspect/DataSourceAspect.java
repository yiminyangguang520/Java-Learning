package org.shiro.security.datasources.aspect;

import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.shiro.security.datasources.DataSourceNames;
import org.shiro.security.datasources.DynamicDataSource;
import org.shiro.security.datasources.annotation.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:49:43
 * 类说明：多数据源，切面处理类
 */
@Aspect
@Component
public class DataSourceAspect implements Ordered {

  protected Logger logger = LoggerFactory.getLogger(getClass());

  @Pointcut("@annotation(org.shiro.security.datasources.annotation.DataSource)")
  public void dataSourcePointCut() {

  }

  @Around("dataSourcePointCut()")
  public Object around(ProceedingJoinPoint point) throws Throwable {
    MethodSignature signature = (MethodSignature) point.getSignature();
    Method method = signature.getMethod();

    DataSource ds = method.getAnnotation(DataSource.class);
    if (ds == null) {
      DynamicDataSource.setDataSource(DataSourceNames.FIRST);
      logger.debug("set datasource is " + DataSourceNames.FIRST);
    } else {
      DynamicDataSource.setDataSource(ds.name());
      logger.debug("set datasource is " + ds.name());
    }

    try {
      return point.proceed();
    } finally {
      DynamicDataSource.clearDataSource();
      logger.debug("clean datasource");
    }
  }

  @Override
  public int getOrder() {
    return 1;
  }
}
