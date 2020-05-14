package com.github.lybgeek.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class WeightController {


  @GetMapping(value = "/test/v1")
  public String testV1(HttpServletRequest request) {
    System.out.println("/test/v1:" + request.getHeader("hello"));
    return "/test/v1";
  }

  @GetMapping(value = "v1")
  public String v1() {
    System.out.println("v1");
    return "/v1";
  }


}
