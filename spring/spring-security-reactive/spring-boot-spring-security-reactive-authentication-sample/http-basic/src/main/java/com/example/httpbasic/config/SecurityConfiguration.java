package com.example.httpbasic.config;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.ReactiveAuthenticationManagerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;

/**
 * @author litz-a
 */

@Slf4j
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

  public ReactiveAuthenticationManagerAdapter anonymousManager() {
    return new ReactiveAuthenticationManagerAdapter(
        new ProviderManager(
            Arrays.asList(new AnonymousAuthenticationProvider("ANON"))
        ));
  }

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

    return http
        .authorizeExchange()
        .pathMatchers("/*.js").permitAll()
        .pathMatchers("/*").hasRole("USER")
        .pathMatchers("/hello").hasRole("USER")
        .and()
        .formLogin().authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/hello"))
        .and()
//        .httpBasic()
//        .and()
        .build();
  }
}
