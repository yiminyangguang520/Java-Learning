package com.kfit.test.web;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kfit.test.bean.Demo;
import com.kfit.test.service.DemoService;

@RestController
@RequestMapping("/demo2")
public class Demo2Controller {

  @Resource
  private DemoService demoService;

  /**
   * 测试保存数据方法.
   */
  @RequestMapping("/save")
  public String save() {
    Demo d = new Demo();
    d.setName("Angel");
    demoService.save(d);//保存数据.
    return "ok.Demo2Controller.save";
  }

}
