package com.javasampleapproach.couchbase;

import com.javasampleapproach.couchbase.model.Customer;
import com.javasampleapproach.couchbase.repo.CustomerRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author litz-a
 */
@SpringBootApplication
public class SpringBootCouchbaseApplication implements CommandLineRunner {

  @Autowired
  private CustomerRepository customerRepository;

  public static void main(String[] args) {
    SpringApplication.run(SpringBootCouchbaseApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    // delete all documents
    customerRepository.deleteAll();

    // save Customer to Couchbase
    customerRepository.saveAll(Arrays.asList(new Customer("01", "Jack", "Smith"),
        new Customer("02", "Adam", "Johnson"),
        new Customer("03", "Kim", "Smith"),
        new Customer("04", "David", "Williams"),
        new Customer("05", "Peter", "Davis")));

    List<Customer> customers = customerRepository.findByLastName("Smith");
    customers.forEach(System.out::println);

    System.out.println("=============Find All Customer=============");
    List<Customer> custs = (List<Customer>) customerRepository.findAll();
    custs.forEach(System.out::println);

    System.out.println("=============findByLastName('Smith')=============");
    custs = customerRepository.findByLastName("Smith");
    custs.forEach(System.out::println);

  }
}
