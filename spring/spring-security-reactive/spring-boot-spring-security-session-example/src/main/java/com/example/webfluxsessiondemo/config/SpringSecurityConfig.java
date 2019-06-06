package com.example.webfluxsessiondemo.config;

import com.example.webfluxsessiondemo.security.CustomReactiveUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;

/**
 * @author wangxing
 * @create 2019/5/15
 */
@Configuration
public class SpringSecurityConfig {

  @Bean
  public ReactiveUserDetailsService getUserDetailsService() {
    return new CustomReactiveUserDetailsService();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity http) {
    final SecurityWebFilterChain securityWebFilterChain = http
        .authorizeExchange()
        .pathMatchers("/login").permitAll()
        .pathMatchers("/user/me").permitAll()
        .anyExchange().authenticated()
        .and()
        .formLogin()
        .authenticationManager(userDetailsRepositoryReactiveAuthenticationManager())
        .securityContextRepository(webSessionServerSecurityContextRepository())
        .and()
        .csrf().disable()
        .build();
    return securityWebFilterChain;
  }

  @Bean
  public WebSessionServerSecurityContextRepository webSessionServerSecurityContextRepository() {
    return new WebSessionServerSecurityContextRepository();
  }

  @Bean
  public UserDetailsRepositoryReactiveAuthenticationManager userDetailsRepositoryReactiveAuthenticationManager() {
    return new UserDetailsRepositoryReactiveAuthenticationManager(getUserDetailsService());
  }

}