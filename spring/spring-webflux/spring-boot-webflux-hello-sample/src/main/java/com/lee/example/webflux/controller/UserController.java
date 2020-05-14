package com.lee.example.webflux.controller;

import com.alibaba.fastjson.JSON;
import com.lee.example.webflux.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author lee
 * @date 2020-03-27
 */
@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private ReactiveStringRedisTemplate reactiveStringRedisTemplate;

  @GetMapping("/hello")
  public Mono<String> hello() {
    return Mono.just("Hello, Reactive");
  }

  @PostMapping("/save")
  public Mono<Boolean> saveUser(@RequestBody User user) {
    ReactiveHashOperations hashOperations = reactiveStringRedisTemplate.opsForHash();
    return hashOperations.put("USER_HS", String.valueOf(user.getId()), JSON.toJSONString(user));
  }

  @GetMapping("/info/{id}")
  public Mono<User> info(@PathVariable Integer id) {
    ReactiveHashOperations reactiveHashOperations = reactiveStringRedisTemplate.opsForHash();
    Mono<String> hval = reactiveHashOperations.get("USER_HS", String.valueOf(id));
    return hval.map(e -> JSON.parseObject(e, User.class));
  }

}
