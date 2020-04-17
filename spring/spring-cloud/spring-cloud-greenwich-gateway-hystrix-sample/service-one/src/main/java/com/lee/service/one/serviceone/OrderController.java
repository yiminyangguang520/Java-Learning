package com.lee.service.one.serviceone;

import java.util.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RequestMapping("/order")
@RestController
public class OrderController {

  @RequestMapping("/info")
  public String orderInfo() {
    return "order info date : " + new Date().toString();
  }
}
