package com.example.webfluxsessiondemo.controller;

import com.example.webfluxsessiondemo.model.User;
import com.example.webfluxsessiondemo.repo.UserRepo;
import com.example.webfluxsessiondemo.security.userdetails.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author wangxing
 * @create 2019/5/15
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private ServerSecurityContextRepository serverSecurityContextRepository;

  @Autowired
  private ReactiveAuthenticationManager reactiveAuthenticationManager;

  @PutMapping("/update")
  public Mono<String> update(@RequestBody User user, ServerWebExchange serverWebExchange) {
    return ReactiveSecurityContextHolder.getContext()
        .map(securityContext -> ((UserInfo<User>) securityContext.getAuthentication().getPrincipal()).getUser())
        .doOnNext(u -> {
          user.setUserName(u.getUserName());
          user.setId(u.getId());
          userRepo.save(user);
        })
        .flatMap(user1 -> autoLogin(user1.getUserName()))
        .thenReturn("success");
  }

  private Mono<Void> autoLogin(final String username) {
    return Mono.subscriberContext()
        .filter(context -> context.hasKey(ServerWebExchange.class))
        .flatMap(context ->
            attemptUpdateAuthentication(username)
                .flatMap(authentication -> {
                  final SecurityContextImpl securityContext = new SecurityContextImpl();
                  securityContext.setAuthentication(authentication);
                  ServerWebExchange serverWebExchange = context.get(ServerWebExchange.class);
                  return serverSecurityContextRepository.save(serverWebExchange, securityContext);
                })
        );
  }

  private Mono<Authentication> attemptUpdateAuthentication(final String userName) {
    final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, "123456");
    return reactiveAuthenticationManager.authenticate(token);
  }

  @GetMapping("/me")
  public Mono<User> me() {
    return ReactiveSecurityContextHolder.getContext()
        .map(securityContext -> ((UserInfo<User>) securityContext.getAuthentication().getPrincipal()).getUser())
        ;
  }

}