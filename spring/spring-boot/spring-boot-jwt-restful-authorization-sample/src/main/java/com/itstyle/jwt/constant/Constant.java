package com.itstyle.jwt.constant;

/**
 * 系统级静态变量
 * @author litz-a
 */
public class Constant {

  /**
   * 刷新TOKEN(有返回数据)
   */
  public static final int RESCODE_REFTOKEN_MSG = 1006;

  /**
   * 刷新TOKEN
   */
  public static final int RESCODE_REFTOKEN = 1007;

  /**
   * Token不存在
   */
  public static final int JWT_ERRCODE_NULL = 4000;

  /**
   * Token过期
   */
  public static final int JWT_ERRCODE_EXPIRE = 4001;

  /**
   * 验证不通过
   */
  public static final int JWT_ERRCODE_FAIL = 4002;

  /**
   * JWT密匙
   */
  public static final String JWT_SECERT = "8677df7fc3a34e26a61c034d5ec8245d";

  /**
   * token有效时间
   */
  public static final long JWT_TTL = 3 * 24 * 60 * 60 * 1000;

  /**
   * token有效期（小时）
   */
  public static final int TOKEN_EXPIRES_HOUR = 3 * 24 * 60 * 60 * 1000;

  /**
   * email
   */
  public static final String EMAIL_DOMAIN = "@glodon.com";

  /**
   * 存储当前登录用户id的字段名
   */
  public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

  /**
   * 存放Authorization的header字段
   */
  public static final String AUTHORIZATION = "authorization";

  /**
   * 存放Authorization的header字段
   */
  public static final String TOKEN_HEADER = "token";

  /**
   * jwt 签发者
   */
  public static final String JWT_ISSUER = "GSPP";
}
