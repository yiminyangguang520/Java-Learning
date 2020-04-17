package com.michaelcgood.controller;

import com.michaelcgood.dao.AccountRepository;
import com.michaelcgood.dao.UsernamesRepository;
import com.michaelcgood.model.Usernames;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author min
 */
@RestController
@RequestMapping("/{userId}/usernames")
public class UsernamesRestController {

  private final UsernamesRepository usernamesRepository;
  private final AccountRepository accountRepository;

  @Autowired
  UsernamesRestController(UsernamesRepository usernamesRepository, AccountRepository accountRepository) {
    this.usernamesRepository = usernamesRepository;
    this.accountRepository = accountRepository;
  }

  @GetMapping
  Collection<Usernames> readUsernames(@PathVariable String userId) {
    this.validateUser(userId);
    return this.usernamesRepository.findByAccountUsername(userId);
  }

  @PostMapping
  ResponseEntity<?> add(@PathVariable String userId, @RequestBody Usernames input) {
    this.validateUser(userId);

    return this.accountRepository.findByUsername(userId)
        .map(account -> {
          Usernames result = usernamesRepository.save(new Usernames(account, input.url, input.username));

          URI url = ServletUriComponentsBuilder
              .fromCurrentRequest().path("/{id}")
              .buildAndExpand(result.getId()).toUri();

          return ResponseEntity.created(url).build();
        })
        .orElse(ResponseEntity.noContent().build());
  }

  @GetMapping(value = "{usernamesId}")
  Optional<Usernames> readUsername(@PathVariable String userId, @PathVariable Long usernameId) {
    this.validateUser(userId);
    return this.usernamesRepository.findById(usernameId);
  }

  private void validateUser(String userId) {
    this.accountRepository.findByUsername(userId).orElseThrow(
        () -> new UserNotFoundException(userId));
  }

}
