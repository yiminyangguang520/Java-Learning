package com.mayi.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author min
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class BussnessserviceUserClientFeignApplication {

  public static void main(String[] args) {
    SpringApplication.run(BussnessserviceUserClientFeignApplication.class, args);
  }
}
