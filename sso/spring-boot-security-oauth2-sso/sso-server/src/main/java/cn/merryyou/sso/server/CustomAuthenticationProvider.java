package cn.merryyou.sso.server;

import cn.merryyou.sso.constant.Domain;
import cn.merryyou.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

/**
 * @author litz-a
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  private UserService userService;

  @Override
  public Authentication authenticate(Authentication auth) throws AuthenticationException {
    String username = auth.getName();
    String password = auth.getCredentials().toString();

    String result = Domain.DOMAIN_ACCOUNT_AUTHENTICATE_RESULT_ERROR;
    try {
      result = userService.domainAccountAuthenticate(username, password);
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (Domain.DOMAIN_ACCOUNT_AUTHENTICATE_RESULT_PASS.equals(result)) {
      return new UsernamePasswordAuthenticationToken(username, password,
          AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
    } else {
      throw new BadCredentialsException("External system authentication failed");
    }
  }

  @Override
  public boolean supports(Class<?> auth) {
    return auth.equals(UsernamePasswordAuthenticationToken.class);
  }
}