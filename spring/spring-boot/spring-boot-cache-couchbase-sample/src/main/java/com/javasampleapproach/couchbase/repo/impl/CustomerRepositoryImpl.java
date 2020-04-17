package com.javasampleapproach.couchbase.repo.impl;

import com.javasampleapproach.couchbase.model.Customer;
import com.javasampleapproach.couchbase.repo.CustomerRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * @author min
 */
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

  private static Map<Long, Customer> store = new HashMap<>();

  static {
    store.put(1L, new Customer("1", "Jack", "Smith"));
    store.put(2L, new Customer("2", "Adam", "Johnson"));
  }

  @Override
  public Customer getById(long id) {
    System.out.println("Service processing...");

    // provide a delay time for simulating slowly processing
    try {
      Thread.sleep(3000);
    } catch (Exception e) {
    }

    Customer cust = store.get(id);
    return cust;
  }

  @Override
  public Customer put(String firstName, long id) {
    Customer cust = store.get(id);
    cust.setFirstName(firstName);

    return cust;
  }
}
