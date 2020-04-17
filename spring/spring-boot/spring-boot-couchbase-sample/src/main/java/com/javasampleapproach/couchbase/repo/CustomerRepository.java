package com.javasampleapproach.couchbase.repo;

import com.javasampleapproach.couchbase.model.Customer;
import java.util.List;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

/**
 * @author min
 */
@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "customer", viewName = "all")
public interface CustomerRepository extends CouchbaseRepository<Customer, String> {

  /**
   * findByLastName
   * @param name
   * @return
   */
  List<Customer> findByLastName(String name);
}
