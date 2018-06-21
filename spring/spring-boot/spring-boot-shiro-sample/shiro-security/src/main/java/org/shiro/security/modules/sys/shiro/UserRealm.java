package org.shiro.security.modules.sys.shiro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.shiro.security.common.utils.Constant;
import org.shiro.security.modules.sys.dao.SysMenuDao;
import org.shiro.security.modules.sys.dao.SysUserDao;
import org.shiro.security.modules.sys.entity.SysMenuEntity;
import org.shiro.security.modules.sys.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:11:36 类说明：自定义realm
 */
@Component
public class UserRealm extends AuthorizingRealm {

  @Autowired
  private SysUserDao sysUserDao;
  @Autowired
  private SysMenuDao sysMenuDao;


  /**
   * 授权(验证权限时调用)
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    SysUserEntity user = (SysUserEntity) principals.getPrimaryPrincipal();
    Long userId = user.getUserId();
    List<String> permsList;
    // 系统管理员，拥有最高权限
    if (userId == Constant.SUPER_ADMIN) {
      List<SysMenuEntity> menuList = sysMenuDao.selectList(null);
      permsList = new ArrayList<>(menuList.size());
      for (SysMenuEntity menu : menuList) {
        permsList.add(menu.getPerms());
      }
    } else {
      permsList = sysUserDao.queryAllPerms(userId);
    }
    // 用户权限列表
    Set<String> permsSet = new HashSet<>();
    for (String perms : permsList) {
      if (StringUtils.isBlank(perms)) {
        continue;
      }
      permsSet.addAll(Arrays.asList(perms.trim().split(",")));
    }
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    info.setStringPermissions(permsSet);
    return info;
  }

  /**
   * 认证(登录时调用)
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
      throws AuthenticationException {
    UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
    // 查询用户信息
    SysUserEntity user = new SysUserEntity();
    user.setUsername(token.getUsername());
    user = sysUserDao.selectOne(user);

    // 账号不存在
    if (user == null) {
      throw new UnknownAccountException("账号或密码不正确");
    }

    // 账号锁定
    if (user.getStatus() == 0) {
      throw new LockedAccountException("账号已被锁定,请联系管理员");
    }

    SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(),
        ByteSource.Util.bytes(user.getSalt()), getName());
    return info;
  }

  @Override
  public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
    HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
    shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
    shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
    super.setCredentialsMatcher(shaCredentialsMatcher);
  }
}
