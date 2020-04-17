package com.lee.service.one.serviceone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author min
 */
@EnableEurekaClient
@SpringBootApplication
public class ServiceOneApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServiceOneApplication.class, args);
  }
}
