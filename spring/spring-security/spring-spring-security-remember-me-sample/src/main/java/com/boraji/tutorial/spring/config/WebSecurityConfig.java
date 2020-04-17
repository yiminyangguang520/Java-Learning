package com.boraji.tutorial.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @author min
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery("select username, password, enabled from users where username = ?")
        .authoritiesByUsernameQuery("select username, authority from authorities where username = ?")
        .passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER");

    http.authorizeRequests().antMatchers("/login**").permitAll();

    http.formLogin().loginPage("/login").loginProcessingUrl("/loginAction").permitAll();

    http.logout().logoutSuccessUrl("/login").permitAll();

    http.rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository());

    http.csrf().disable();
  }

  @Bean
  public PersistentTokenRepository tokenRepository() {
    JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl=new JdbcTokenRepositoryImpl();
    jdbcTokenRepositoryImpl.setCreateTableOnStartup(true);
    jdbcTokenRepositoryImpl.setDataSource(dataSource);
    return jdbcTokenRepositoryImpl;
  }
}
