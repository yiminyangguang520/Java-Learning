package com.lee.example.restservice;

import com.lee.example.restservice.hello.GreetingWebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lee
 * @date 2020-03-25
 */
@SpringBootApplication
public class ReactiveRestServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ReactiveRestServiceApplication.class, args);

    GreetingWebClient gwc = new GreetingWebClient();
    System.out.println(gwc.getResult());
  }

}
