package org.jdonee.cooking.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.jdonee.cooking.domain.UserInfo;
import org.jdonee.cooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 验证用户登录
 *
 * @author Administrator
 */
@Component("userRealm")
public class UserRealm extends AuthorizingRealm {

  @Autowired
  private UserService userService;

  public UserRealm() {
    setName("UserRealm");
    // 采用MD5加密
    setCredentialsMatcher(new HashedCredentialsMatcher("md5"));
  }

  /**
   * 权限资源角色
   * @param principals
   * @return
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    String username = (String) principals.getPrimaryPrincipal();
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    info.setStringPermissions(userService.findPermissions(username));
    return info;
  }

  /**
   * 登录验证
   * @param token
   * @return
   * @throws AuthenticationException
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    UsernamePasswordToken upt = (UsernamePasswordToken) token;
    String userName = upt.getUsername();
    UserInfo user = userService.findByAccount(userName);
    if (user == null) {
      throw new UnknownAccountException();
    }
    SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, user.getPassword(), getName());
    return info;
  }
}