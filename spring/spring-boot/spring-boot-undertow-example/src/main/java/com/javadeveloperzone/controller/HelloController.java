package com.javadeveloperzone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author JavaDeveloperZone
 * @date 19-07-2017
 */
@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    return "This response using undertow server.";
  }
}


