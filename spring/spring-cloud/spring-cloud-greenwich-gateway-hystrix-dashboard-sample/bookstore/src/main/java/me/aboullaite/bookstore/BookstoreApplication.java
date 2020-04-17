package me.aboullaite.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author min
 */
@EnableEurekaClient
@SpringBootApplication
public class BookstoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(BookstoreApplication.class, args);
  }

}

