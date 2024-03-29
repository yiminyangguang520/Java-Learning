package murraco.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author min
 */

public enum Role implements GrantedAuthority {
  ROLE_ADMIN, ROLE_CLIENT;

  @Override
  public String getAuthority() {
    return name();
  }

}
