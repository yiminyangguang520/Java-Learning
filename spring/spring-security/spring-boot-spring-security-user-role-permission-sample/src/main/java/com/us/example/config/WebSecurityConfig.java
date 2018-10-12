package com.us.example.config;

import com.us.example.service.CustomFilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author litz-a
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private CustomFilterSecurityInterceptor customFilterSecurityInterceptor;

  @Autowired
  private UserDetailsService customUserService;


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customUserService)
        .passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/css/**").permitAll()
        .anyRequest().authenticated() //任何请求,登录后可以访问
        .and()
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/")
        .failureUrl("/login?error")
        .permitAll() //登录页面用户任意访问
        .and()
        .logout().permitAll(); //注销行为任意访问
    http.addFilterBefore(customFilterSecurityInterceptor, FilterSecurityInterceptor.class);
  }

}
