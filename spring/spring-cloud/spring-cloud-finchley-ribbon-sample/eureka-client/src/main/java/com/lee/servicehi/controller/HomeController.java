package com.lee.servicehi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
public class HomeController {

  @Value("${server.port}")
  private String port;

  @RequestMapping("/hi")
  public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
    return "hi " + name + " ,i am from port:" + port;
  }
}
