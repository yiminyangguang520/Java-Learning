package com.lee.servicefeign.web;

import com.lee.servicefeign.clients.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 **/
@RestController
public class HiController {


  /**
   * 编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
   */
  @Autowired
  SchedualServiceHi schedualServiceHi;

  @GetMapping(value = "/hi")
  public String sayHi(@RequestParam String name) {
    return schedualServiceHi.sayHiFromClientOne(name);
  }
}