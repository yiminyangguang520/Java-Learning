package com.lee.example.webflux.controller;

import com.lee.example.webflux.domain.Coffee;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring WebFlux is the new reactive web framework introduced in Spring Framework 5.0.
 * Unlike Spring MVC, it does not require the Servlet API, is fully asynchronous and non-blocking,
 * and implements the Reactive Streams specification through the Reactor project.
 *
 * @author lee
 * @date 2020-03-27
 */
@RestController
@RequestMapping("/coffees")
public class CoffeeController {

  private final ReactiveRedisOperations<String, Coffee> coffeeOps;

  public CoffeeController(ReactiveRedisOperations<String, Coffee> coffeeOps) {
      this.coffeeOps = coffeeOps;
  }

  @GetMapping("/getAll")
  public Flux<Coffee> getAll() {
      return coffeeOps.keys("*").flatMap(coffeeOps.opsForValue()::get);
  }

  @GetMapping("/info/{id}")
  public Mono<Coffee> info(@PathVariable String id) {
      ReactiveValueOperations valueOperations = coffeeOps.opsForValue();
      return valueOperations.get(id);
  }
}
