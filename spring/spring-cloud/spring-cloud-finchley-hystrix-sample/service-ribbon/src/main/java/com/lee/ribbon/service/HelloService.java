package com.lee.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author litz-a
 */
@Service
public class HelloService {

  @Autowired
  RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "hiError")
  public String hiService(String name) {
    // service-client应与服务提供者的spring.application.name的值一样
    return restTemplate.getForObject("http://service-client/hi?name=" + name, String.class);
  }

  public String hiError(String name) {
    return "hi, " + name + ", sorry, error!";
  }
}
