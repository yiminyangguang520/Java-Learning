package com.algerfan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * @author bruce
 */
@SpringBootApplication
@EnableReactiveMongoRepositories
public class WebfluxApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebfluxApplication.class, args);
  }
}
