package com.javasampleapproach.springsecurity.customizelogout.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author min
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(4);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/", "/logoutsuccessful").permitAll()
        .antMatchers("/admin").hasRole("ADMIN")
        .anyRequest().authenticated();

    http.formLogin()
        .loginPage("/login")
        .successForwardUrl("/")
        .permitAll();

    http.logout()
        .logoutSuccessHandler(customizeLogoutSuccessHandler)
        .permitAll();

    http.exceptionHandling()
        .accessDeniedPage("/403");
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .inMemoryAuthentication()
        .withUser("user").password(passwordEncoder.encode("user")).roles("USER")
        .and()
        .withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN");
  }
}