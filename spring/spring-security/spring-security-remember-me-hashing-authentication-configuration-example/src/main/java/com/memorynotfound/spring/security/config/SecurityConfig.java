package com.memorynotfound.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author litz-a
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
            .antMatchers(
                    "/js/**",
                    "/*.js",
                    "/css/**",
                    "/img/**",
                    "/webjars/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
          .anyRequest().authenticated()
      .and()
          .formLogin()
              .loginPage("/login")
//              .successForwardUrl("/")
                  .permitAll()
      .and()
          .logout()
              .invalidateHttpSession(true)
              .clearAuthentication(true)
              .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
              .logoutSuccessUrl("/login?logout")
                  .permitAll()
      .and()
          .rememberMe()
              .key("unique-and-secret")
              .rememberMeCookieName("remember-me-cookie-name")
              .tokenValiditySeconds(24 * 60 * 60);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("user")
        .password("$2a$10$m1wR6Y3xm7q6d9z6TEZiAugfKHZILWmNNnioIG6UVSLzE8br9tzGi")
        .roles("USER");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}