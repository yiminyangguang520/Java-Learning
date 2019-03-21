package com.example.formlogin.config;

import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

/**
 * @author litz-a
 */
@Slf4j
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

    return http
        .authorizeExchange()
        .pathMatchers("/login",
            "/bye",
            "/favicon.ico",
            "/images/**",
            "/*.js")
        .permitAll()
        .pathMatchers("/**")
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/"))
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessHandler(logoutSuccessHandler("/bye"))
        .and()
        .csrf()
        .and()
        .build();
  }

  public ServerLogoutSuccessHandler logoutSuccessHandler(String uri) {
    RedirectServerLogoutSuccessHandler successHandler = new RedirectServerLogoutSuccessHandler();
    successHandler.setLogoutSuccessUrl(URI.create(uri));
    return successHandler;
  }

}
