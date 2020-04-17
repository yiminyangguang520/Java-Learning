package com.javasampleapproach.rabbitmq.web;

import com.javasampleapproach.rabbitmq.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
public class WebController {

  @Autowired
  private Producer producer;

  @RequestMapping("/send")
  public String sendMsg(@RequestParam("msg") String msg) {
    producer.produceMsg(msg);
    return "Done";
  }
}
