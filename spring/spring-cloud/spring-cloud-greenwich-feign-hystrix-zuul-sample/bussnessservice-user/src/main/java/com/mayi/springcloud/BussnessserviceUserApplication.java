package com.mayi.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author litz-a
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BussnessserviceUserApplication {

  public static void main(String[] args) {
    SpringApplication.run(BussnessserviceUserApplication.class, args);
  }
}
