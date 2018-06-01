package org.shiro.security.modules.job.task;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.shiro.security.modules.sys.entity.SysUserEntity;
import org.shiro.security.modules.sys.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:27:54
 * 类说明：测试定时任务
 */
@Component("testTask")
public class TestTask {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private SysUserService sysUserService;

  public void test(String params) {
    logger.info("我是带参数的test方法，正在被执行，参数为：" + params);

    try {
      Thread.sleep(1000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    SysUserEntity user = sysUserService.selectById(1L);
    System.out.println(ToStringBuilder.reflectionToString(user));

  }


  public void test2() {
    logger.info("我是不带参数的test2方法，正在被执行");
  }
}
