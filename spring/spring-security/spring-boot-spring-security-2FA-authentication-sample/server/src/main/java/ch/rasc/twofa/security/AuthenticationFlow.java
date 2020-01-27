package ch.rasc.twofa.security;

/**
 * @author litz-a
 */

public enum AuthenticationFlow {
  NOT_AUTHENTICATED, AUTHENTICATED, TOTP, TOTP_ADDITIONAL_SECURITY;
}
