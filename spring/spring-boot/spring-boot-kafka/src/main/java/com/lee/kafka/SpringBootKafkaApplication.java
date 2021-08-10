package com.lee.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * @author min
 */
@EnableKafka
@SpringBootApplication
public class SpringBootKafkaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootKafkaApplication.class, args);
  }

}
