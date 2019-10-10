package com.example.webfluxdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * @author litz-a
 */
@SpringBootApplication
@EnableReactiveMongoRepositories
public class WebfluxDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebfluxDemoApplication.class, args);
  }
}
