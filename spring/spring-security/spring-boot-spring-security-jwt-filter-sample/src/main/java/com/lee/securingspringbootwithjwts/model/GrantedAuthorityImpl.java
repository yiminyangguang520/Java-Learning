package com.lee.securingspringbootwithjwts.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author min
 */
@Getter
@Setter
@AllArgsConstructor
public class GrantedAuthorityImpl implements GrantedAuthority {

  private String authority;
}
