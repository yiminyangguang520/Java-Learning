package com.glodon.securingspringbootwithjwts.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author litz-a
 */
@Getter
@Setter
@AllArgsConstructor
public class GrantedAuthorityImpl implements GrantedAuthority {

  private String authority;
}