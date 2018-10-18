package com.hellowood.springsecurity.security;

import com.hellowood.springsecurity.model.UserModel;
import com.hellowood.springsecurity.service.UserService;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

/**
 * The type Custom authentication provider.
 *
 * @author HelloWood
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private HttpSession session;

  @Autowired
  private UserService userService;

  /**
   * Validate user info is correct form database
   */
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();

    // Check username and password is correct
    UserModel user = userService.loadUserByUsernameAndPassword(username, password);
    if (user == null) {
      logger.error("user {} login failed, username or password is wrong", username);
      throw new BadCredentialsException("Username or password is not correct");
    } else if (!user.getEnabled()) {
      logger.error("user {} login failed, this account had expired", username);
      throw new AccountExpiredException("Account had expired");
    }

    // If user is valid put user info to session
    session.setAttribute("user", user);
    Authentication auth = new UsernamePasswordAuthenticationToken(username, password, AuthorityUtils.NO_AUTHORITIES);
    return auth;
  }


  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }

}
