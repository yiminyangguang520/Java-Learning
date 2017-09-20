package com.kfit.test.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.kfit.test.bean.Demo;

/**
 * 测试.
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

  /**
   * 返回demo数据: 请求地址：http://127.0.0.1:8080/demo/getDemo
   */
  @RequestMapping("/getDemo")
  public Demo getDemo() {
    Demo demo = new Demo();
    demo.setId(1);
    demo.setName("Angel");
    return demo;
  }

  //地址：http://127.0.0.1:8080/demo/getFastJson
  @RequestMapping("/getFastJson")
  public String getFastJson() {
    Demo demo = new Demo();
    demo.setId(2);
    demo.setName("Angel2");
    return JSONObject.toJSONString(demo);
  }

  @RequestMapping("/zeroException")
  public int zeroException() {
    return 100 / 0;
  }

}
