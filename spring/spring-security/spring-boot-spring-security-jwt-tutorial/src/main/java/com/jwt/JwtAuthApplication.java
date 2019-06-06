package com.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main implementation class which serves two purpose in a spring boot application: Configuration and bootstrapping.
 *
 * @author yatin-batra
 */
@SpringBootApplication
public class JwtAuthApplication {

  public static void main(String[] args) {
    SpringApplication.run(JwtAuthApplication.class, args);
  }
}