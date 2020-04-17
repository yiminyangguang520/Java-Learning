package com.arnoldgalovics.blog.feigninterceptor.testapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author min
 */
@SpringBootApplication
@EnableFeignClients("com.arnoldgalovics.blog.feigninterceptor.testapp.client")
public class TestApplication {

  public static void main(String[] args) {
    SpringApplication.run(TestApplication.class, args);
  }
}
