package com.lee.servicehi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author litz-a
 */
@SpringBootApplication
@EnableEurekaClient
public class ServiceClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServiceClientApplication.class, args);
  }
}



