package com.example.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author min
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Bean
  public TokenStore tokenStore() {
    return new InMemoryTokenStore();
  }

  @Bean
  public BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .passwordEncoder(encoder())
        .withUser("user_1").password(encoder().encode("123456")).roles("USER")
        .and()
        .withUser("user_2").password(encoder().encode("123456")).roles("ADMIN");

  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http.csrf().disable()
        .requestMatchers()
        .antMatchers("/oauth/authorize")
        .and()
        .authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .formLogin().permitAll();
    // @formatter:on
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }


}