package com.example.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.ServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.WebSessionServerCsrfTokenRepository;

/**
 * @author min
 */

@Slf4j
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

  @Bean
  public ServerCsrfTokenRepository csrfTokenRepository() {
    WebSessionServerCsrfTokenRepository repository = new WebSessionServerCsrfTokenRepository();
    repository.setHeaderName("X-CSRF-TK");

    return repository;
  }

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

    return http
        .authorizeExchange()
        .pathMatchers("/who*")
        .hasRole("USER")
        .pathMatchers("/primes")
        .hasRole("USER")
        .pathMatchers("/admin")
        .access((mono, context) -> mono
            .map(auth -> auth.getAuthorities().stream()
                .filter(e -> e.getAuthority().equals("ROLE_ADMIN"))
                .count() > 0)
            .map(AuthorizationDecision::new)
        )
        .and()
        .csrf()
        .csrfTokenRepository(csrfTokenRepository())
        .and()
        .httpBasic()
        .and()
        .build();
  }
}
