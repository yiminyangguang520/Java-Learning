package com.lee.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author min
 */
@EnableScheduling
@SpringBootApplication
public class AsyncApplication {

  public static void main(String[] args) {
    SpringApplication.run(AsyncApplication.class, args);
  }

}
