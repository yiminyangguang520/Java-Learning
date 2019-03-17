package com.thoughtmechanix.specialroutes;

import com.thoughtmechanix.specialroutes.utils.UserContextFilter;
import javax.servlet.Filter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @author litz-a
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class SpecialRouteApplication {

  @Bean
  public Filter userContextFilter() {
    UserContextFilter userContextFilter = new UserContextFilter();
    return userContextFilter;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpecialRouteApplication.class, args);
  }
}
