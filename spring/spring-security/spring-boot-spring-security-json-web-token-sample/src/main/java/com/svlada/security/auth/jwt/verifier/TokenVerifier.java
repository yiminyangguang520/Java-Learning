package com.svlada.security.auth.jwt.verifier;

/**
 * @author vladimir.stankovic
 *
 * Aug 17, 2016
 */
public interface TokenVerifier {

  /**
   * verify
   */
  boolean verify(String jti);
}
