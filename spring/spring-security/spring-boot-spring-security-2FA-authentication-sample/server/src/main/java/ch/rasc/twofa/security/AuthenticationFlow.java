package ch.rasc.twofa.security;

/**
 * @author min
 */

public enum AuthenticationFlow {
  NOT_AUTHENTICATED, AUTHENTICATED, TOTP, TOTP_ADDITIONAL_SECURITY;
}
