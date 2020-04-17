package com.javasampleapproach.couchbase.service;

import com.javasampleapproach.couchbase.model.Customer;
import com.javasampleapproach.couchbase.service.cache.CustomerCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author min
 */
@Service
public class CustomerServices {

  @Autowired
  private CustomerCache customerCache;

  public Customer get(long id) {
    return customerCache.getOnCache(id);
  }

  public Customer putCustomer(String firstName, long id) {
    return customerCache.putOnCache(firstName, id);
  }

  public void evict(long id) {
    customerCache.evict(id);
  }
}
