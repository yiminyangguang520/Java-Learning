package com.windmt.controller;

import com.windmt.domain.Account;
import com.windmt.repository.AccountMongoReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @author yibo
 */
@RequestMapping("/account")
@RestController
public class AccountController {

    @Autowired
    private AccountMongoReactiveRepository repository;

    @GetMapping("/customer/{customer}")
    public Flux<Account> findByCustomer(@PathVariable(name = "customer") String customer) {
        System.out.println("Customer => " + customer + " [ " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS")) + " ]");
        return repository.findByCustomerId(customer);
    }

    @PostMapping("")
    public Mono<Account> createAccount(@RequestBody Account account) {
        return repository.save(account);
    }

}
