package com.memorynotfound.ldap.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

/**
 * @author litz-a
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
          .antMatchers("/managers").hasRole("MANAGERS")
          .antMatchers("/employees").hasRole("EMPLOYEES")
          .anyRequest().fullyAuthenticated()
        .and()
          .formLogin();
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .ldapAuthentication()
          .userDnPatterns("uid={0},ou=people")
          .groupSearchBase("ou=groups")
          .userSearchFilter("uid={0}")
          .groupSearchBase("ou=groups")
          .groupSearchFilter("uniqueMember={0}")
        .contextSource(contextSource())
        .passwordCompare()
          .passwordEncoder(new LdapShaPasswordEncoder())
          .passwordAttribute("userPassword");
  }

  @Bean
  public DefaultSpringSecurityContextSource contextSource() {
    return new DefaultSpringSecurityContextSource(
        Collections.singletonList("ldap://localhost:12345"), "dc=memorynotfound,dc=com");
  }

}