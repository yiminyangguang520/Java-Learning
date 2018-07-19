package com.gcr.sso.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author litz-a
 */
@SpringBootApplication
public class GcrClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(GcrClientApplication.class, args);
  }

}