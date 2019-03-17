package com.thoughtmechanix.organization;

import com.thoughtmechanix.organization.utils.UserContextFilter;
import javax.servlet.Filter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class Application {

  @Bean
  public Filter userContextFilter() {
    UserContextFilter userContextFilter = new UserContextFilter();
    return userContextFilter;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
