package com.xingfly.xfauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class XfAuthApplication {

  public static void main(String[] args) {
    SpringApplication.run(XfAuthApplication.class, args);
  }
}