package com.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author litz-a
 */
@Configuration
@EnableWebSecurity
public class Securityconfig extends WebSecurityConfigurerAdapter {

  @Bean
  public JwtAuthfilter jwtAuthenticationFilter() {
    return new JwtAuthfilter();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/token")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .csrf().disable()
        .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }
}