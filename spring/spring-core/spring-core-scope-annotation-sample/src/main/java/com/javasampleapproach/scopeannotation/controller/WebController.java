package com.javasampleapproach.scopeannotation.controller;

import com.javasampleapproach.scopeannotation.bean.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
public class WebController {

  @Autowired
  private Customer customer;

  @RequestMapping("/address")
  public String address() {
    String result = customer.getAddress();
    customer.setAddress("EU");
    return "init Data: " + result + "|-----| modified Data: " + customer.getAddress();
  }

  @RequestMapping("/addresscheck")
  public String addresscheck() {
    return "check Data: " + customer.getAddress();
  }

  @RequestMapping("/age")
  public String age() {
    String result = customer.getAge();
    customer.setAge("25");
    return "init Data: " + result + "|-----| modified Data: " + customer.getAge();
  }

  @RequestMapping("/agecheck")
  public String agecheck() {
    return "check Data: " + customer.getAge();
  }
}
