package com.lee.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author min
 */
@SpringBootApplication
@EnableScheduling
public class DeadLetterExampleService {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(DeadLetterExampleService.class, args);
  }

}
