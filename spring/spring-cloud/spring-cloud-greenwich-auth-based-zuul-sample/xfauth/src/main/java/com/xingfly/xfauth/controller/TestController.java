package com.xingfly.xfauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by SuperS on 2017/9/26.
 *
 * @author SuperS
 */
@RestController
public class TestController {

  @GetMapping("/test")
  public String test() {
    return "test";
  }
}
