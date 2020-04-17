package com.javasampleapproach.cors.service;

import com.javasampleapproach.cors.model.Customer;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author min
 */
@Service
public class CustomerService {

  public List<Customer> getCustomers() {

    List<Customer> list = new ArrayList<>();
    list.add(new Customer(1L, "Jack"));
    list.add(new Customer(2L, "Adam"));
    list.add(new Customer(3L, "Kim"));

    return list;
  }
}
