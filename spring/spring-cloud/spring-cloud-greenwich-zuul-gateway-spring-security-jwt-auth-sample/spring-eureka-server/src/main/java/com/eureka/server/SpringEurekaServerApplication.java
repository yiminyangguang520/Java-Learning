package com.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author litz-a
 */
@SpringBootApplication
@EnableEurekaServer    // Enable eureka server

public class SpringEurekaServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringEurekaServerApplication.class, args);
  }
}
