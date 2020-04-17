package com.javasampleapproach.corsjavaconfig.controller;

import com.javasampleapproach.corsjavaconfig.model.Customer;
import com.javasampleapproach.corsjavaconfig.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
public class WebController {

  @Autowired
  private CustomerService service;

  @RequestMapping("customers")
  public List<Customer> getCustomers() {

    List<Customer> list = service.getCustomers();
    return list;
  }

  @RequestMapping("data")
  public List<Customer> getData() {

    List<Customer> list = service.getCustomers();
    list.forEach(item -> item.setName(item.getName().toUpperCase()));

    return list;
  }
}