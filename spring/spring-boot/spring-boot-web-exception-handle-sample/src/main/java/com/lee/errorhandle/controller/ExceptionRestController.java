package com.lee.errorhandle.controller;

import com.lee.errorhandle.exception.Http401Exception;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 * @date 2018-05-20
 */

@RestController
public class ExceptionRestController {

  @RequestMapping("/hello")
  public String hello() {
    throw new RuntimeException("This is Hello Exception");
  }

  @RequestMapping(value = "/401", produces = {MediaType.TEXT_HTML_VALUE})
  public String http401() throws Http401Exception {
    throw new Http401Exception("This HTTP 401 Exception");
  }

  @RequestMapping(value = "/access")
  public String accessError() {
    throw new IllegalAccessError("Illegal Access in RestController");
  }

  @RequestMapping(value = "/interceptError")
  public String interceptError() {
    throw new IllegalAccessError("IllegalAccessError in RestController");
  }

  @RequestMapping(value = "/interceptPostError")
  public String interceptPostError() {
    return "OK";
  }
}
