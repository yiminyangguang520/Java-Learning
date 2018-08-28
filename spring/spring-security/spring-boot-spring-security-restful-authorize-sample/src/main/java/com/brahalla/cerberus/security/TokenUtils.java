package com.brahalla.cerberus.security;

import com.brahalla.cerberus.model.security.CerberusUser;

import io.jsonwebtoken.*;
import java.io.UnsupportedEncodingException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author litz-a
 */
@Component
public class TokenUtils {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final String AUDIENCE_UNKNOWN   = "unknown";
  private final String AUDIENCE_WEB       = "web";
  private final String AUDIENCE_MOBILE    = "mobile";
  private final String AUDIENCE_TABLET    = "tablet";

  @Value("${cerberus.token.secret}")
  private String secret;

  @Value("${cerberus.token.expiration}")
  private Long expiration;

  public String getUsernameFromToken(String token) {
    String username;
    try {
      final Claims claims = this.getClaimsFromToken(token);
      username = claims.getSubject();
    } catch (Exception e) {
      username = null;
    }
    return username;
  }

  public Date getCreatedDateFromToken(String token) {
    Date created;
    try {
      final Claims claims = this.getClaimsFromToken(token);
      created = new Date((Long) claims.get("created"));
    } catch (Exception e) {
      created = null;
    }
    return created;
  }

  public Date getExpirationDateFromToken(String token) {
    Date expiration;
    try {
      final Claims claims = this.getClaimsFromToken(token);
      expiration = claims.getExpiration();
    } catch (Exception e) {
      expiration = null;
    }
    return expiration;
  }

  public String getAudienceFromToken(String token) {
    String audience;
    try {
      final Claims claims = this.getClaimsFromToken(token);
      audience = (String) claims.get("audience");
    } catch (Exception e) {
      audience = null;
    }
    return audience;
  }

  private Claims getClaimsFromToken(String token) {
    Claims claims;
    try {
      claims = Jwts.parser()
        .setSigningKey(this.secret.getBytes("UTF-8"))
        .parseClaimsJws(token)
        .getBody();
    } catch (Exception e) {
      claims = null;
    }
    return claims;
  }

  private Date generateCurrentDate() {
    return new Date(System.currentTimeMillis());
  }

  private Date generateExpirationDate() {
    return new Date(System.currentTimeMillis() + this.expiration * 1000);
  }

  private Boolean isTokenExpired(String token) {
    final Date expiration = this.getExpirationDateFromToken(token);
    return expiration.before(this.generateCurrentDate());
  }

  private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
    return (lastPasswordReset != null && created.before(lastPasswordReset));
  }

  private String generateAudience(Device device) {
    String audience = this.AUDIENCE_UNKNOWN;
    if (device.isNormal()) {
      audience = this.AUDIENCE_WEB;
    } else if (device.isTablet()) {
      audience = AUDIENCE_TABLET;
    } else if (device.isMobile()) {
      audience = AUDIENCE_MOBILE;
    }
    return audience;
  }

  private Boolean ignoreTokenExpiration(String token) {
    String audience = this.getAudienceFromToken(token);
    return (this.AUDIENCE_TABLET.equals(audience) || this.AUDIENCE_MOBILE.equals(audience));
  }

  public String generateToken(UserDetails userDetails, Device device) {
    Map<String, Object> claims = new HashMap<String, Object>();
    claims.put("sub", userDetails.getUsername());
    claims.put("audience", this.generateAudience(device));
    claims.put("created", this.generateCurrentDate());
    return this.generateToken(claims);
  }

  private String generateToken(Map<String, Object> claims) {
      try {
          return Jwts.builder()
                  .setClaims(claims)
                  .setExpiration(this.generateExpirationDate())
                  .signWith(SignatureAlgorithm.HS512, this.secret.getBytes("UTF-8"))
                  .compact();
      } catch (UnsupportedEncodingException ex) {
          //didn't want to have this method throw the exception, would rather log it and sign the token like it was before
          logger.warn(ex.getMessage());
          return Jwts.builder()
                  .setClaims(claims)
                  .setExpiration(this.generateExpirationDate())
                  .signWith(SignatureAlgorithm.HS512, this.secret)
                  .compact();
      }
  }

  public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
    final Date created = this.getCreatedDateFromToken(token);
    return (!(this.isCreatedBeforeLastPasswordReset(created, lastPasswordReset)) && (!(this.isTokenExpired(token)) || this.ignoreTokenExpiration(token)));
  }

  public String refreshToken(String token) {
    String refreshedToken;
    try {
      final Claims claims = this.getClaimsFromToken(token);
      claims.put("created", this.generateCurrentDate());
      refreshedToken = this.generateToken(claims);
    } catch (Exception e) {
      refreshedToken = null;
    }
    return refreshedToken;
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    CerberusUser user = (CerberusUser) userDetails;
    final String username = this.getUsernameFromToken(token);
    final Date created = this.getCreatedDateFromToken(token);
    final Date expiration = this.getExpirationDateFromToken(token);
    return (username.equals(user.getUsername()) && !(this.isTokenExpired(token)) && !(this.isCreatedBeforeLastPasswordReset(created, user.getLastPasswordReset())));
  }

}
