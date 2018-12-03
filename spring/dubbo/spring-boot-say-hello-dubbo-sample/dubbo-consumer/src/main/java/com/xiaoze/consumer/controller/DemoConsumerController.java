package com.xiaoze.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaoze.api.service.DemoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaoze
 * @date 2018/6/7
 */
@RestController
public class DemoConsumerController {

  /**
   * 申明为一个reference，其实就是设置一个bean类了，
   * 将原来xml配置替换成注解而已
   * <dubbo:reference id=“xxxService” interface=“com.xxx.XxxService” />
   */
  @Reference(version = "${demo.service.version}",
      application = "${dubbo.application.id}",
      url = "dubbo://localhost:20880")
  private DemoService demoService;

  @RequestMapping("/sayHello/{name}")
  public String sayHello(@PathVariable("name") String name) {
    return demoService.sayHello(name);
  }

}
