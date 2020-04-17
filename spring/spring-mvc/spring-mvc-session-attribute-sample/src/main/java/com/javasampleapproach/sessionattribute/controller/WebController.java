package com.javasampleapproach.sessionattribute.controller;

import com.javasampleapproach.sessionattribute.model.Counter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * @author min
 */
@RestController
public class WebController {

  @RequestMapping("/")
  public String handle(@SessionAttribute("counter") Counter counter) {

    counter.setCount(counter.getCount() + 1);

    return counter.toString();
  }
}
