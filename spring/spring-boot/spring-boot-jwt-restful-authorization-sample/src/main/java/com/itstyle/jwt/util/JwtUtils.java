package com.itstyle.jwt.util;

import com.itstyle.jwt.constant.Constant;
import com.itstyle.jwt.vo.ValidateResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.util.encoders.Base64;

/**
 * jwt加密和解密的工具类
 * @author litz-a
 */
public class JwtUtils {

  /**
   * 签发JWT
   * @param id 用户ID
   * @param subject 可以是JSON数据 尽可能少
   * @param ttlMillis
   * @return
   */
  public static String createJWT(String id, String subject, long ttlMillis) {
    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);
    SecretKey secretKey = generalKey();
    JwtBuilder builder = Jwts.builder()
        .setId(id)
        .setSubject(subject)
        .setIssuer(Constant.JWT_ISSUER)
        .setIssuedAt(now)
        .signWith(SignatureAlgorithm.HS256, secretKey);
    if (ttlMillis >= 0) {
      long expMillis = nowMillis + ttlMillis;
      Date expDate = new Date(expMillis);
      builder.setExpiration(expDate);
    }
    return builder.compact();
  }

  /**
   * 验证JWT
   */
  public static ValidateResult validateJWT(String jwtStr) {
    ValidateResult validateResult = new ValidateResult();
    Claims claims;
    try {
      claims = parseJWT(jwtStr);
      validateResult.setSuccess(true);
      validateResult.setClaims(claims);
    } catch (ExpiredJwtException e) {
      validateResult.setErrCode(Constant.JWT_ERRCODE_EXPIRE);
      validateResult.setSuccess(false);
    } catch (SignatureException e) {
      validateResult.setErrCode(Constant.JWT_ERRCODE_FAIL);
      validateResult.setSuccess(false);
    } catch (Exception e) {
      validateResult.setErrCode(Constant.JWT_ERRCODE_FAIL);
      validateResult.setSuccess(false);
    }
    return validateResult;
  }

  public static SecretKey generalKey() {
    byte[] encodedKey = Base64.decode(Constant.JWT_SECERT);
    SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    return key;
  }

  /**
   * 解析JWT字符串
   */
  public static Claims parseJWT(String jwt) {
    SecretKey secretKey = generalKey();
    return Jwts.parser()
        .setSigningKey(secretKey)
        .parseClaimsJws(jwt)
        .getBody();
  }

  public static void main(String[] args) {
    ValidateResult validateResult = validateJWT("");
    System.out.println(validateResult.toString());
  }
}
