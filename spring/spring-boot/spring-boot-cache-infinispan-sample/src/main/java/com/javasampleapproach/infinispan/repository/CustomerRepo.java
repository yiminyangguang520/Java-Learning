package com.javasampleapproach.infinispan.repository;

import com.javasampleapproach.infinispan.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author min
 */
@Service
@CacheConfig(cacheNames = "customer")
public class CustomerRepo {

  Logger log = LoggerFactory.getLogger(this.getClass());

  @Cacheable(key = "#id")
  public Customer findByCode(long id) {
    log.info("---> Loading customer with id '" + id + "'");
    return new Customer(id);
  }

  @CachePut(key = "#id")
  public Customer putCustomerToCache(long id) {
    String info = String.format("---> PUT customer with id = %d to Cache", id);
    log.info(info);
    return new Customer(id);
  }

  @CacheEvict(allEntries = true)
  public void evictAllEntries() {
    log.info("---> Evict All Entries.");
  }

  @CacheEvict(key = "#id")
  public void evictEntry(long id) {
    log.info("---> Evict Customer with id = " + id);
  }
}