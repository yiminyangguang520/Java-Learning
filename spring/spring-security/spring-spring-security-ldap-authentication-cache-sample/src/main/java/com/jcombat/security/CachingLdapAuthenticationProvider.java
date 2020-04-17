package com.jcombat.security;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.authentication.LdapAuthenticator;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;

/**
 * @author min
 */
public class CachingLdapAuthenticationProvider extends LdapAuthenticationProvider {

  private static Log log = LogFactory.getLog(CachingLdapAuthenticationProvider.class);

  private Map<String, Authentication> cachedAuthentications = new ConcurrentHashMap<>();
  private Map<String, Date> cachedWhen = new ConcurrentHashMap<>();
  private long timeToRefreshAuthentication = TimeUnit.MINUTES.toMillis(10);

  public CachingLdapAuthenticationProvider(LdapAuthenticator authenticator) {
    super(authenticator);
  }

  public CachingLdapAuthenticationProvider(LdapAuthenticator authenticator,
      LdapAuthoritiesPopulator authoritiesPopulator) {
    super(authenticator, authoritiesPopulator);
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String userName = authentication.getName();
    Authentication result = cachedAuthentications.get(userName);
    Date lastRefresh = cachedWhen.get(userName);
    if (result != null && (System.currentTimeMillis() - lastRefresh.getTime()) < getTimeToRefreshAuthentication()) {
      log.debug("Cache hit for " + userName + " last refresh " + lastRefresh);
      return result;
    }

    log.debug((lastRefresh == null ? "Initial authentication for " + userName : "Refresh authentication for " + userName));

    result = super.authenticate(authentication);
    cachedAuthentications.put(userName, result);
    cachedWhen.put(userName, new Date());
    return result;
  }

  public void setTimeToRefreshAuthentication(long timeToRefreshAuthentication) {
    this.timeToRefreshAuthentication = timeToRefreshAuthentication;
  }

  public long getTimeToRefreshAuthentication() {
    return timeToRefreshAuthentication;
  }

}
