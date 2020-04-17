package com.javasampleapproach.rabbitmq;

import com.javasampleapproach.rabbitmq.model.Company;
import com.javasampleapproach.rabbitmq.model.Product;
import com.javasampleapproach.rabbitmq.producer.Producer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author min
 */
@SpringBootApplication
public class SpringRabbitMqProducerApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(SpringRabbitMqProducerApplication.class, args);
  }

  @Autowired
  Producer producer;

  @Override
  public void run(String... args) throws Exception {
    /*
     * Init Java objects
     */
    Product iphone7 = new Product("Iphone 7");
    Product iPadPro = new Product("IPadPro");

    List<Product> appleProducts = new ArrayList<Product>(Arrays.asList(iphone7, iPadPro));

    Company apple = new Company("Apple", appleProducts);

    iphone7.setCompany(apple);
    iPadPro.setCompany(apple);

    /*
     * send message to RabbitMQ
     */
    producer.produce(apple);
  }
}