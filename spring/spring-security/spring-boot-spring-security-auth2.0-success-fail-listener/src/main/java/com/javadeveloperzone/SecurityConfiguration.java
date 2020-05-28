package com.javadeveloperzone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author JavaDeveloperZone
 * @date 09-12-2017
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  /**
   * 密码加密器
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * 认证管理器
   */
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .antMatcher("/**")
        .authorizeRequests()
        .antMatchers("/", "/login**")
        .permitAll()
        .anyRequest()
        .authenticated();
  }

  @Autowired      // here is configuration related to spring boot basic authentication
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("zone1").password(passwordEncoder().encode("mypassword")).roles("USER")
        .and()
        .withUser("zone2").password(passwordEncoder().encode("mypassword")).roles("USER")
        .and()
        .withUser("zone3").password(passwordEncoder().encode("mypassword")).roles("USER")
        .and()
        .withUser("zone4").password(passwordEncoder().encode("mypassword")).roles("USER")
        .and()
        .withUser("zone5").password(passwordEncoder().encode("mypassword")).roles("USER");
  }
}
