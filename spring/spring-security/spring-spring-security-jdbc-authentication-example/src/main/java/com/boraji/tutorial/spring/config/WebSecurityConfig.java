package com.boraji.tutorial.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author min
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  @Qualifier("mysqlDataSource")
  private DataSource dataSource;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery("select username, password, enabled"
            + " from users where username=?")
        .authoritiesByUsernameQuery("select username, authority "
            + "from authorities where username=?")
        .passwordEncoder(new BCryptPasswordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests()
        .antMatchers("/console/**").permitAll()
        .anyRequest().hasAnyRole("ADMIN", "USER")
    .and()
    // Authenticate users with HTTP basic authentication
    .httpBasic()
        .realmName("jdbc-authentication");

    // add this line to use H2 web console
    http.headers().frameOptions().disable();
  }


  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers("/*.js");
  }
}
