package com.memorynotfound.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// uncomment if you want to use Spring Security XML Configuration
// @ImportResource("classpath:spring-security-config.xml")
public class Run {

  public static void main(String[] args) {
    SpringApplication.run(Run.class, args);
  }
}
