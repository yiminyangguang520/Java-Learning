package com.wanari.customlogin.example.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanari.customlogin.example.config.security.filter.RequestBodyReaderAuthenticationFilter;
import com.wanari.customlogin.example.domain.User;
import com.wanari.customlogin.example.service.UserServiceImpl;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author bruce
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserServiceImpl userService;
  private final ObjectMapper objectMapper;
  private final PasswordEncoder passwordEncoder;

  public WebSecurityConfig(UserServiceImpl userService, ObjectMapper objectMapper, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.objectMapper = objectMapper;
    this.passwordEncoder = passwordEncoder;
  }

  @Bean
  public RequestBodyReaderAuthenticationFilter authenticationFilter() throws Exception {
    RequestBodyReaderAuthenticationFilter authenticationFilter = new RequestBodyReaderAuthenticationFilter();
    authenticationFilter.setAuthenticationSuccessHandler(this::loginSuccessHandler);
    authenticationFilter.setAuthenticationFailureHandler(this::loginFailureHandler);
    authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    authenticationFilter.setAuthenticationManager(authenticationManagerBean());
    return authenticationFilter;
  }

  @Bean
  public DaoAuthenticationProvider authProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userService);
    authProvider.setPasswordEncoder(passwordEncoder);
    return authProvider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authProvider());
  }

  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/admin/h2/**").permitAll()
        .anyRequest().authenticated()

        .and()
        .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessHandler(this::logoutSuccessHandler)

        .and()
        .exceptionHandling()
        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

    // Enable <frameset> in order to use H2 web console
    http.headers().frameOptions().disable();
  }


  private void loginSuccessHandler(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication) throws IOException {

    User loggedInUser = userService.findByLogin(authentication.getName())
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + authentication.getName()));
    response.setStatus(HttpStatus.OK.value());
    objectMapper.writeValue(response.getWriter(), loggedInUser);
  }

  private void loginFailureHandler(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException e) throws IOException {

    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    objectMapper.writeValue(response.getWriter(), "Nopity nop!");
  }

  private void logoutSuccessHandler(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication) throws IOException {

    response.setStatus(HttpStatus.OK.value());
    objectMapper.writeValue(response.getWriter(), "Bye!");
  }
}
