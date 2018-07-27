package com.javadeveloperzone.security;

import com.javadeveloperzone.handler.CustomAuthenticationFailureHandler;
import com.javadeveloperzone.handler.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Java Developer Zone
 * @date 15-11-2017
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * here is configuration related to spring boot basic authentication
   * @param auth
   * @throws Exception
   */
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
          .withUser("zone")
          .password(bCryptPasswordEncoder().encode("mypassword"))
          .roles("USER")
        .and()
          .withUser("zone2")
          .password(bCryptPasswordEncoder().encode("mypassword"))
          .roles("ADMIN");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
          .authorizeRequests().antMatchers("/loginFailed").permitAll()
        .and()
          .authorizeRequests()
          .anyRequest().authenticated()
        .and()
          .formLogin()
          .successHandler(new CustomAuthenticationSuccessHandler())
          .failureHandler(new CustomAuthenticationFailureHandler())
          .loginPage("/login")
          .permitAll();
  }
}