package com.lee.service.one.serviceone;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RequestMapping("/user")
@RestController
public class UserController {

  @RequestMapping("who")
  public String who() {
    return "my name is lee";
  }
}
