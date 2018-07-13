package com.javasampleapproach.infinispan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author litz-a
 */
@SpringBootApplication
@EnableCaching
public class SpringCachingInfinispanApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringCachingInfinispanApplication.class, args);
  }
}