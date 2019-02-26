package net.ameizi.springboot.jwt.sample.system.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.Base64UrlCodec;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.ameizi.springboot.jwt.sample.system.exception.TokenParserException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Component;

@Data
@Slf4j
@Component
@ConfigurationProperties
public class JwtUtil {

  /**
   * 加密字符串
   */
  @Value("${jwt.config.secret}")
  private String secret;

  /**
   * 过期时间。单位为秒
   */
  @Value("${jwt.config.ttl}")
  private long ttl;

  private static final String AUDIENCE_UNKNOWN = "unknown";
  private static final String AUDIENCE_WEB = "web";
  private static final String AUDIENCE_MOBILE = "mobile";
  private static final String AUDIENCE_TABLET = "tablet";

  private static final String CLAIM_KEY_CREATED = "created";
  private static final String ROLES = "roles";

  private Base64UrlCodec base64UrlCodec = new Base64UrlCodec();

  /**
   * 生成签名的Key
   */
  private SecretKey generalKey() {
    byte[] encodedKey = base64UrlCodec.decode(secret);
    return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
  }

  /**
   * 创建jwt token
   *
   * iss：Issuer，发行者 sub：Subject，主题 aud：Audience，观众 exp：Expiration time，过期时间 nbf：Not before iat：Issued at，发行时间 jti：JWT ID
   */
  public String generateToken(String id, String subject, String roles, Device device) {
    Date now = new Date();

    Claims claims = Jwts.claims();
    claims.put(Claims.SUBJECT, subject);
    claims.put(Claims.ID, id);
    claims.put(ROLES, roles);
    claims.put(Claims.AUDIENCE, generateAudience(device));
    claims.put(Claims.ISSUED_AT, now);

    claims.setId(id);
    claims.setSubject(subject);
    claims.setIssuedAt(now);
    claims.setIssuer(subject);

    return this.generateToken(claims);
  }


  /**
   * 通过spring-mobile-device的device检测访问主体
   */
  private String generateAudience(Device device) {
    String audience = AUDIENCE_UNKNOWN;
    if (device.isNormal()) {
      audience = AUDIENCE_WEB;//PC端
    } else if (device.isTablet()) {
      audience = AUDIENCE_TABLET;//平板
    } else if (device.isMobile()) {
      audience = AUDIENCE_MOBILE;//手机
    }
    return audience;
  }


  /**
   * 创建jwt token
   */
  private String generateToken(Map<String, Object> claims) {
    return Jwts.builder()
        .setClaims(claims)
        .setExpiration(generateExpirationDate())
        .signWith(SignatureAlgorithm.HS512, generalKey())
        .compact();
  }


  /**
   * 生成token时间 = 当前时间 + expiration（properties中配置的失效时间）
   */
  private Date generateExpirationDate() {
    return new Date(System.currentTimeMillis() + this.ttl * 1000);
  }

  // /**
  //  * 校验jwt token是否过期
  //  *
  //  * @param token
  //  * @return
  //  */
  // public Boolean validateToken(String token) {
  //     final Date expiration = getExpirationDateFromToken(token);
  //     return expiration.before(new Date());
  // }


  /**
   * 校验jwt token是否过期
   */
  public Claims validateToken(String token) {
    Claims result = null;
    try {
      Claims claims = getClaimsFromToken(token);
      Date expiration = claims.getExpiration();
      if (expiration.after(new Date())) {
        result = claims;
      }
    } catch (Exception e) {
      result = null;
    }
    return result;
  }


  /**
   * 获取token失效时间
   */
  public Date getExpirationDateFromToken(String token) {
    Date expiration;
    try {
      final Claims claims = getClaimsFromToken(token);
      expiration = claims.getExpiration();
    } catch (Exception e) {
      expiration = null;
    }
    return expiration;
  }


  /**
   * 从token中获取登录用户名
   */
  public String getLoginNameFromToken(String token) {
    return this.getClaimsFromToken(token).getSubject();
  }


  /**
   * 解析jwt token字符串
   */
  public Claims getClaimsFromToken(String token) {
    try {
      return Jwts.parser()
          .setSigningKey(generalKey())
          .parseClaimsJws(token)
          .getBody();
    } catch (ExpiredJwtException e) {
      log.error("token已过期。", e);
      throw new TokenParserException("token已过期。");
    } catch (UnsupportedJwtException e) {
      log.error("token非法。", e);
      throw new TokenParserException("token非法。");
    } catch (MalformedJwtException e) {
      log.error("jwt未能正常被构造。", e);
      throw new TokenParserException("jwt未能正常被构造。");
    } catch (SignatureException e) {
      log.error("签名计算失败。", e);
      throw new TokenParserException("签名计算失败。");
    } catch (IllegalArgumentException e) {
      log.error("token参数非法。", e);
      throw new TokenParserException("token参数非法。");
    } catch (Exception e) {
      log.error("解析JWT TOKEN字符串错误。", e);
      throw new TokenParserException("解析JWT TOKEN字符串错误。");
    }
  }


  /**
   * 刷新Token
   */
  public String refreshToken(String token) {
    final Claims claims = this.getClaimsFromToken(token);
    claims.put(CLAIM_KEY_CREATED, new Date());
    return this.generateToken(claims);
  }

}
