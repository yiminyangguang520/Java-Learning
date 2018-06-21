package org.shiro.security.modules.sys.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.shiro.security.modules.sys.entity.SysUserEntity;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:38:34
 * 类说明：Shiro工具类
 */
public class ShiroUtils {

  /**
   * 加密算法
   */
  public final static String hashAlgorithmName = "SHA-256";
  /**
   * 循环次数
   */
  public final static int hashIterations = 16;

  public static String sha256(String password, String salt) {
    return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
  }

  public static Session getSession() {
    return SecurityUtils.getSubject().getSession();
  }

  public static Subject getSubject() {
    return SecurityUtils.getSubject();
  }

  public static SysUserEntity getUserEntity() {
    return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
  }

  public static Long getUserId() {
    return getUserEntity().getUserId();
  }

  public static void setSessionAttribute(Object key, Object value) {
    getSession().setAttribute(key, value);
  }

  public static Object getSessionAttribute(Object key) {
    return getSession().getAttribute(key);
  }

  public static boolean isLogin() {
    return SecurityUtils.getSubject().getPrincipal() != null;
  }

  public static void logout() {
    SecurityUtils.getSubject().logout();
  }

}
