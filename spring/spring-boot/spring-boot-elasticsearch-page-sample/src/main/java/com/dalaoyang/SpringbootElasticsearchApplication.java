package com.dalaoyang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author litz-a
 */
@SpringBootApplication
@EnableElasticsearchRepositories
public class SpringbootElasticsearchApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootElasticsearchApplication.class, args);
  }
}
