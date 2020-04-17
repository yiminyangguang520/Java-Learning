package com.eureka.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author min
 */
@SpringBootApplication
@EnableEurekaClient
public class SpringEurekaAuthApp {

  public static void main(String[] args) {
    SpringApplication.run(SpringEurekaAuthApp.class, args);
  }
}
