package com.mayi.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author litz-a
 */
@SpringBootApplication
@EnableEurekaServer
public class CommonserviceEurekaApplication {

  public static void main(String[] args) {
    SpringApplication.run(CommonserviceEurekaApplication.class, args);
  }
}
