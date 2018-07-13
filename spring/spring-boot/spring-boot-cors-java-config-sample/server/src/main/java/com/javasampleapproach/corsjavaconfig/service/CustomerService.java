package com.javasampleapproach.corsjavaconfig.service;

import com.javasampleapproach.corsjavaconfig.model.Customer;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
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
