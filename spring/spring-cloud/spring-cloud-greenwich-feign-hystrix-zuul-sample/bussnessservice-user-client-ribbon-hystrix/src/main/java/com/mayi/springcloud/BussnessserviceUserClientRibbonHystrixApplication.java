package com.mayi.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author litz-a
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class BussnessserviceUserClientRibbonHystrixApplication {

  public static void main(String[] args) {
    SpringApplication.run(BussnessserviceUserClientRibbonHystrixApplication.class, args);
  }

  @Bean
  @LoadBalanced
  RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
