package org.shiro.security.modules.job.utils;

import java.lang.reflect.Method;
import org.apache.commons.lang.StringUtils;
import org.shiro.common.exception.ShiroException;
import org.shiro.common.utils.SpringContextUtils;
import org.springframework.util.ReflectionUtils;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:19:21
 * 类说明： 执行定时任务
 */
public class ScheduleRunnable implements Runnable {

  private Object target;
  private Method method;
  private String params;

  public ScheduleRunnable(String beanName, String methodName, String params) throws NoSuchMethodException, SecurityException {
    this.target = SpringContextUtils.getBean(beanName);
    this.params = params;

    if (StringUtils.isNotBlank(params)) {
      this.method = target.getClass().getDeclaredMethod(methodName, String.class);
    } else {
      this.method = target.getClass().getDeclaredMethod(methodName);
    }
  }

  @Override
  public void run() {
    try {
      ReflectionUtils.makeAccessible(method);
      if (StringUtils.isNotBlank(params)) {
        method.invoke(target, params);
      } else {
        method.invoke(target);
      }
    } catch (Exception e) {
      throw new ShiroException("执行定时任务失败", e);
    }
  }

}
