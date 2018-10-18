package com.michaelcgood;

import com.michaelcgood.dao.AccountRepository;
import com.michaelcgood.dao.UsernamesRepository;
import com.michaelcgood.model.Account;
import com.michaelcgood.model.Usernames;
import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author litz-a
 */
@SpringBootApplication
public class SpringBootActuatorExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootActuatorExampleApplication.class, args);
  }

  @Bean
  CommandLineRunner init(AccountRepository accountRepository,
      UsernamesRepository usernamesRepository) {
    return (evt) -> Arrays.asList(
        "ricksanchez,mortysmith,bethsmith,jerrysmith,summersmith,birdperson,squanchy,picklerick".split(","))
        .forEach(
            a -> {
              Account account = accountRepository.save(new Account(a, "password"));
              usernamesRepository.save(new Usernames(account, "http://example.com/login", a + "1"));
              usernamesRepository.save(new Usernames(account, "http://example2.com/login", "the_" + a));
            });
  }
}
