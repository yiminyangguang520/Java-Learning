package com.lee.gateway.test.gatewayclient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author min
 */
@RestController
public class FallBackController {

  @RequestMapping("/user/fallback")
  public Mono<String> fallback() {
    return Mono.just("service error, jump fallback");
  }
}
