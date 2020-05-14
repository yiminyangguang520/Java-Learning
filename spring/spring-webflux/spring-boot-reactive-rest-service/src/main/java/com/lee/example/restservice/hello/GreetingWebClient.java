package com.lee.example.restservice.hello;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author lee
 * @date 2020-03-25
 */
public class GreetingWebClient {

  /**
   * For reactive applications, Spring offers the WebClient class, which is non-blocking.
   * <p>
   * WebClient can be used to communicate with non-reactive, blocking services, too.
   */
  private final WebClient client = WebClient.create("http://localhost:8080");

  private final Mono<ClientResponse> result = client.get()
      .uri("/hello")
      .accept(MediaType.TEXT_PLAIN)
      .exchange();

  public String getResult() {
    return ">> result = " + result.flatMap(res -> res.bodyToMono(String.class)).block();
  }
}
