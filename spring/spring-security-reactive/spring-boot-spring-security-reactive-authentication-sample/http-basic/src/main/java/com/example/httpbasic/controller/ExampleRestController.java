package com.example.httpbasic.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author litz-a
 */
@RestController
public class ExampleRestController {

  @Bean
  RouterFunction<?> routes() {
    return RouterFunctions
        .route(RequestPredicates.GET("/hello"),
            r -> ServerResponse
                .ok()
                .body(r.principal()
                        .repeat()
                        .zipWith(
                            Mono.just("Hello "),
                            (pp, str) -> str + pp.getName()),
                    String.class)

        );
  }

}