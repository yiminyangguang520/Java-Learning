package com.example.webfluxsessiondemo;

import com.example.webfluxsessiondemo.model.User;
import com.example.webfluxsessiondemo.repo.UserRepo;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpCookie;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebfluxSessionDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebfluxSessionDemoApplicationTests {

  @Autowired
  private WebTestClient webTestClient;

  private MultiValueMap<String, ResponseCookie> cookieStore = new LinkedMultiValueMap<>();

  @Autowired
  private UserRepo userRepo;

  @Before
  public void setUp() throws Exception {
    webTestClient = webTestClient.mutate()
        .filter((clientRequest, exchangeFunction) ->
            exchangeFunction.exchange(clientRequest)
                .doOnNext(clientResponse -> {
                  MultiValueMap<String, ResponseCookie> cookies = clientResponse.cookies();
                  cookieStore.putAll(cookies);
                })
        )
        .build();
    login();
    configCookies();
  }

  private void configCookies() {
    webTestClient = webTestClient.mutate()
        .defaultCookies(stringStringMultiValueMap -> {
          cookieStore.forEach((name, responseCookies) -> {
            stringStringMultiValueMap.put(
                name,
                responseCookies
                    .stream()
                    .map(HttpCookie::getValue)
                    .collect(Collectors.toList())
            );
          });
        })
        .build();
  }

  private WebTestClient.BodySpec<String, ?> login() {
    return webTestClient.post()
        .uri("/login")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .syncBody("username=test&password=123456")
        .exchange()
        .expectStatus()
        .isFound()
        .expectBody(String.class)
        ;
  }

  private WebTestClient.BodySpec<User, ?> me() {
    return webTestClient.get()
        .uri("/user/me")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange()
        .expectStatus()
        .is2xxSuccessful()
        .expectBody(User.class)
        ;
  }

  private WebTestClient.BodySpec<String, ?> updateUser(String nickName) {
    User request = new User();
    request.setNickName(nickName);
    return webTestClient.put()
        .uri("/user/update")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .body(Mono.just(request), User.class)
        .exchange()
        .expectStatus()
        .is2xxSuccessful()
        .expectBody(String.class)
        ;
  }


  @Test
  @SneakyThrows
  public void contextLoads() {
    Random random = new Random();
    for (int i = 0; i < 300; i++) {
      String nickName = String.valueOf(random.nextLong());
      updateUser(nickName);
      configCookies();
      String currentNickName = userRepo.getByUserName("test").getNickName();
      assert nickName.equals(currentNickName);
      me().value(Matchers.hasProperty("nickName", Matchers.equalTo(currentNickName)))
      ;
    }
  }

}