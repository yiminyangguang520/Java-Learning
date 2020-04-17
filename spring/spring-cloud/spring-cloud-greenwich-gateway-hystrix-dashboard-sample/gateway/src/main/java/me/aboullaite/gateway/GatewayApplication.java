package me.aboullaite.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

/**
 * @author min
 */
@EnableHystrix
@EnableEurekaClient
@SpringBootApplication
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  // Not
  @Bean
  KeyResolver userKeyResolver() {
    return exchange -> Mono.just("fero");
  }
}

