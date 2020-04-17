package org.newtutorials.rabbitmq.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by dani on 5/22/2017.
 * @author min
 */
@SpringBootApplication
@ComponentScan("org.newtutorials.rabbitmq")
@EnableScheduling
public class Application {

  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(Application.class, args);
  }
}
