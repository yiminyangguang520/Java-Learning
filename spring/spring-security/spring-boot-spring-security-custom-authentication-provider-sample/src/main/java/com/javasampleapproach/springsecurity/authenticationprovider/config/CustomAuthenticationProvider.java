package com.javasampleapproach.springsecurity.authenticationprovider.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author litz-a
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  List<User> users = new ArrayList<>();

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(4);
  }

  @PostConstruct
  void init() {
    users.add(new User("user", "user", "ROLE_USER"));
    users.add(new User("admin", "admin", "ROLE_ADMIN"));
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String name = authentication.getName();
    String password = authentication.getCredentials().toString();

    Optional<User> optionalUser = users.stream().filter(u -> u.index(name, password)).findFirst();

    if (!optionalUser.isPresent()) {
      logger.error("Authentication failed for user = " + name);
      throw new BadCredentialsException("Authentication failed for user = " + name);
    }

    // find out the exited users
    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    grantedAuthorities.add(new SimpleGrantedAuthority(optionalUser.get().role));
    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(name, password,
        grantedAuthorities);

    logger.info("Succesful Authentication with user = " + name);
    return auth;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }

  private class User {

    String name;
    String password;
    String role;

    User(String name, String password, String role) {
      this.name = name;
      this.password = password;
      this.role = role;
    }

    boolean index(String name, String password) {
      return this.name.equals(name) && this.password.equals(password);
    }
  }
}
