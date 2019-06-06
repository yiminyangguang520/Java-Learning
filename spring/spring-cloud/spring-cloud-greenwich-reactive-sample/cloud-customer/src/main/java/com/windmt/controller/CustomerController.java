package com.windmt.controller;

import com.windmt.domain.Customer;
import com.windmt.dto.Account;
import com.windmt.repository.CustomerMongoReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author: yibo
 **/
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerMongoReactiveRepository repository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("")
    public Flux<Customer> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Customer> get(@PathVariable String id) {
        return repository.findById(id);
    }

    @PostMapping("")
    public Mono<Customer> create(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    @PutMapping("/{id}")
    public Mono<Customer> update(@PathVariable("id") String id, @RequestBody Customer customer) {
        customer.setId(id);
        return repository.save(customer);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return repository.deleteById(id);
    }

    @GetMapping("/{id}/account")
    public Flux<Account> getAllAccounts(@PathVariable String id) {
        return webClientBuilder.baseUrl("http://cloud-account/account/").build()
                .get().uri("/customer/" + id)
                .retrieve()
                .bodyToFlux(Account.class);
    }


}
