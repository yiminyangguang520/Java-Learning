package com.example.controller;

import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author litz-a
 */
@RestController
public class IndexController {


  @GetMapping("/who")
  Mono<String> who(@AuthenticationPrincipal UserDetails user) {
    return Mono.just("who: " + user.getUsername());
  }

  @GetMapping("/who-revised")
  Mono<String> whoRevised(@AuthenticationPrincipal UserDetails user) {
    if (User.class.isInstance(user)) {
      return Mono.just(user)
          .ofType(User.class)
          .map(u -> "who/custom: " + u.getUsername());
    }

    return Mono.just("who/map-reactive: " + user.getUsername());
  }

  @Bean
  RouterFunction<?> routes() {
    return RouterFunctions
        .route(RequestPredicates.GET("/admin"),
            r -> ServerResponse
                .ok()
                .body(r.principal()
                        .repeat()
                        .zipWith(
                            Mono.just(" has access."),
                            (pp, str) -> pp.getName() + str),
                    String.class)
        )
        .andRoute(RequestPredicates.GET("/primes"),
            r -> ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(
                    Flux.interval(Duration.ofMillis(250))
                        .filter(this::is_prime)
                        .zipWith(r.principal().repeat(),
                            (n, user) -> Long.toString(n)
                        ),
                    String.class
                )
        );
  }

  // brute-force search :p
  boolean is_prime(long num) {
    if (num <= 1) {
      return false;
    }
    if (num % 2 == 0 && num > 2) {
      return false;
    }
    for (int i = 3; i < num / 2; i += 2) {
      if (num % i == 0) {
        return false;
      }
    }
    return true;
  }
}