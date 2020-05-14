package com.lee.example.restservice.hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author lee
 * @date 2020-03-25
 */
@Component
public class GreetingHandler {

  private final AtomicLong counter = new AtomicLong();

  /**
   * A handler to handle the request and create a response
   */
  public Mono<ServerResponse> hello(ServerRequest request) {
    return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
        .body(BodyInserters.fromValue("Hello, Spring!"));

  }
}
