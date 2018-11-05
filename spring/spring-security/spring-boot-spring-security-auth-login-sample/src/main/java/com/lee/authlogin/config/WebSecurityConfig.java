package com.lee.authlogin.config;

import com.lee.authlogin.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author litz-a
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private CustomUserDetailService customUserDetailService;

  @Autowired
  private AuthenticationSuccessHandler authenticationSuccessHandler;

  @Autowired
  private AuthenticationFailHandler authenticationFailHandler;

  @Autowired
  @Qualifier("authenticationEntryPointImpl")
  private AuthenticationEntryPoint entryPoint;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

    http.csrf().disable();

    http.authorizeRequests()
        .antMatchers("/v2/api-docs/**").permitAll()
        .anyRequest().authenticated();

    http.formLogin()
        .loginProcessingUrl("/api/login")
        .successHandler(authenticationSuccessHandler)
        .failureHandler(authenticationFailHandler);

    http.exceptionHandling().authenticationEntryPoint(entryPoint);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customUserDetailService);
  }

}
