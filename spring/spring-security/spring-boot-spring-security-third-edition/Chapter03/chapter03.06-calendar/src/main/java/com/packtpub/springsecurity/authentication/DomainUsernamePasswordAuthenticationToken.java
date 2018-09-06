package com.packtpub.springsecurity.authentication;

import com.packtpub.springsecurity.domain.CalendarUser;
import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author litz-a
 */
public final class DomainUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

  private final String domain;

  /**
   * used for returning to Spring Security after being used for attempting authentication
   * @param principal
   * @param credentials
   * @param domain
   */
  public DomainUsernamePasswordAuthenticationToken(String principal, String credentials, String domain) {
    super(principal, credentials);
    this.domain = domain;
  }

  /**
   * authenticated
   * @param principal
   * @param credentials
   * @param domain
   * @param authorities
   */
  public DomainUsernamePasswordAuthenticationToken(CalendarUser principal, String credentials, String domain, Collection<? extends GrantedAuthority> authorities) {
    super(principal, credentials, authorities);
    this.domain = domain;
  }

  public String getDomain() {
    return domain;
  }
}