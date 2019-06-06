package com.windmt.repository;

import com.windmt.domain.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;


/**
 * @author yibo
 */
public interface AccountMongoReactiveRepository extends ReactiveCrudRepository<Account, String> {

    Flux<Account> findByCustomerId(String customerId);

}
