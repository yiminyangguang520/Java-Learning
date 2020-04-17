package com.lee.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author min
 */
@Service
public class HelloService {

  @Autowired
  RestTemplate restTemplate;

  public String hiService(String name) {
    // service-client应与服务提供者的spring.application.name的值一样
    return restTemplate.getForObject("http://service-client/hi?name=" + name, String.class);
  }
}
