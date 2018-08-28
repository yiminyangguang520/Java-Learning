package com.yy.example;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by yangyibo on 17/1/17.
 */

@ComponentScan(basePackages = "com.yy.example")
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    ConfigurableApplicationContext run = run(Application.class, args);
  }

}
