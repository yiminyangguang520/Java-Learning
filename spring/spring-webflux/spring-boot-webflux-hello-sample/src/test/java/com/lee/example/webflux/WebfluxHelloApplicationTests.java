package com.lee.example.webflux;

import com.alibaba.fastjson.JSON;
import com.lee.example.webflux.domain.EnhanceAccessToken;
import com.lee.example.webflux.domain.User;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebfluxHelloApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class WebfluxHelloApplicationTests {

  @Autowired
  private ReactiveRedisOperations<String, EnhanceAccessToken> coffeeOps;

  @Autowired
  private ReactiveStringRedisTemplate reactiveStringRedisTemplate;

  @Test
  public void saveUser() {
    User user1 = new User(1, "bruce");
    ReactiveHashOperations hashOperations = reactiveStringRedisTemplate.opsForHash();
    Mono<Boolean> result = hashOperations.put("USER_HS", String.valueOf(user1.getId()), JSON.toJSONString(user1));
    result.subscribe(System.out::println);
  }

  @Test
  void contextLoads() {
    EnhanceAccessToken enhanceAccessToken = new EnhanceAccessToken();
    enhanceAccessToken.setAccess_token("fdsafdsf");
    enhanceAccessToken.setScope("foo");

    ReactiveValueOperations valueOperations = coffeeOps.opsForValue();
    Mono<Boolean> result = valueOperations.set("123456", enhanceAccessToken);
    result.subscribe(System.out::println);

    Mono<EnhanceAccessToken> tokenDtoMono = valueOperations.get("123456");
    tokenDtoMono.subscribe(System.out::println);
  }

}
