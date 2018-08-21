package com.javasampleapproach.requestattribute.controller;

import com.javasampleapproach.requestattribute.model.Counter;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RestController
public class WebController {

  @RequestMapping("/")
  public String handle(@RequestAttribute("counter") Counter counter) {
    counter.setCount(counter.getCount() + 1);
    return counter.toString();
  }
}
