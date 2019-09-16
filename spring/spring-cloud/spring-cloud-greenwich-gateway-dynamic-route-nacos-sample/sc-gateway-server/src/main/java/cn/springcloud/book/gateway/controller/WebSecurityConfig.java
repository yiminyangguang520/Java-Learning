package cn.springcloud.book.gateway.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author bruce
 */
@EnableWebFluxSecurity
public class WebSecurityConfig {

  @Bean
  SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {
    http.authorizeExchange().anyExchange().permitAll()
        .and()
        .cors()
        .and()
        .httpBasic()
        .and()
        .csrf().disable();
    return http.build();
  }
}