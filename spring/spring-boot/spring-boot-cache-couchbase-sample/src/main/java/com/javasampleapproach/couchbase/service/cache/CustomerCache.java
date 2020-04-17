package com.javasampleapproach.couchbase.service.cache;

import com.javasampleapproach.couchbase.config.CacheConfig;
import com.javasampleapproach.couchbase.model.Customer;
import com.javasampleapproach.couchbase.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author min
 */
@Component
public class CustomerCache {

  @Autowired
  CustomerRepository customerRepository;

  @Cacheable(value = CacheConfig.CACHE_NAME, key = "#id")
  public Customer getOnCache(long id) {
    return customerRepository.getById(id);
  }

  @CachePut(value = CacheConfig.CACHE_NAME, key = "#id")
  public Customer putOnCache(String firstName, long id) {
    return customerRepository.put(firstName, id);
  }

  @CacheEvict(value = CacheConfig.CACHE_NAME, key = "#id")
  public void evict(long id) {
  }
}