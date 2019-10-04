package com.lee.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author bruce
 */
@EnableAsync
@SpringBootApplication
public class SpringBootWebAsyncTaskApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootWebAsyncTaskApplication.class, args);
  }

}
