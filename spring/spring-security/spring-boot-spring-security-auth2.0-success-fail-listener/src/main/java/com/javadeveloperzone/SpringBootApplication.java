package com.javadeveloperzone;

import org.springframework.boot.SpringApplication;

/**
 *
 * @author JavaDeveloperZone
 * @date 19-07-2017
 */

@org.springframework.boot.autoconfigure.SpringBootApplication

// Using a root package also allows the @ComponentScan annotation to be used without needing to specify a basePackage attribute
public class SpringBootApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootApplication.class, args);
  }
}
