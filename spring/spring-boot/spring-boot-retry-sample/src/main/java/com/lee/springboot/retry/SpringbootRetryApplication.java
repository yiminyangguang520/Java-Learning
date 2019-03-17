package com.lee.springboot.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author litz-a
 */
@SpringBootApplication
@EnableRetry
public class SpringbootRetryApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootRetryApplication.class, args);
  }
}
