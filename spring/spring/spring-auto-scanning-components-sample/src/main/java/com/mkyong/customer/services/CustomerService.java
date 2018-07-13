package com.mkyong.customer.services;

import com.mkyong.customer.dao.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 */
@Service
public class CustomerService {

  @Autowired
  CustomerDAO customerDAO;

  @Override
  public String toString() {
    return "CustomerService [customerDAO=" + customerDAO + "]";
  }

}
