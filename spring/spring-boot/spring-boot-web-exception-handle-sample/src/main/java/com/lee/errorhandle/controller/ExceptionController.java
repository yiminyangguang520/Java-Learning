package com.lee.errorhandle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author min
 * @date 2018-05-20
 */
@Controller
public class ExceptionController {

  @RequestMapping(value = "/voidaccess")
  public void accessError() {
    throw new IllegalAccessError("Illegal Access in RestController");
  }

}
