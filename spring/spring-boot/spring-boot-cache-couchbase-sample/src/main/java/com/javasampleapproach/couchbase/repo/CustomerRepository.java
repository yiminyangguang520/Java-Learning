package com.javasampleapproach.couchbase.repo;

import com.javasampleapproach.couchbase.model.Customer;

/**
 * @author min
 */
public interface CustomerRepository {

  /**
   * getById
   * @param id
   * @return
   */
  Customer getById(long id);

  /**
   * put
   * @param firstName
   * @param id
   * @return
   */
  Customer put(String firstName, long id);
}
