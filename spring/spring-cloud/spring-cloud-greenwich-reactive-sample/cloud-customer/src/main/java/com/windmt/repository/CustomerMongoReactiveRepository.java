package com.windmt.repository;

import com.windmt.domain.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author: yibo
 **/
public interface CustomerMongoReactiveRepository extends ReactiveCrudRepository<Customer, String> {
}
