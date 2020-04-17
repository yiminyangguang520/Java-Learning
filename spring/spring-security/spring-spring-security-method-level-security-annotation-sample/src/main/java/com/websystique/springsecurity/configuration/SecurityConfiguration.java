package com.websystique.springsecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author min
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("bill").password(passwordEncoder.encode("abc123")).roles("USER");
    auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("root123")).roles("ADMIN");
    auth.inMemoryAuthentication().withUser("dba").password(passwordEncoder.encode("root123")).roles("ADMIN", "DBA");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests()
        .antMatchers("/", "/home").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')");

    http.formLogin()
        .loginPage("/login")
        .usernameParameter("ssoId")
        .passwordParameter("password");

    http.exceptionHandling()
        .accessDeniedPage("/Access_Denied");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(4);
  }
}
