package com.yy.example.security;

import com.yy.example.bean.Permission;
import com.yy.example.bean.User;
import com.yy.example.dao.PermissionDao;
import com.yy.example.dao.UserDao;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author min
 * @date 17/2/7
 */
@Service
public class UrlUserService implements UserDetailsService {

  @Autowired
  UserDao userDao;

  @Autowired
  PermissionDao permissionDao;

  /**
   * 重写loadUserByUsername 方法获得 userdetails 类型用户
   */
  @Override
  public UserDetails loadUserByUsername(String userName) {

    User user = userDao.getByUserName(userName);

    if (user == null) {
      throw new UsernameNotFoundException("admin: " + userName + " do not exist!");
    }

    List<Permission> permissions = permissionDao.getByUserId(user.getId());
    List<GrantedAuthority> grantedAuthorities = permissions.stream()
        .filter(permission -> StringUtils.isNotEmpty(permission.getName()))
        .map(permission -> new UrlGrantedAuthority(permission.getPermissionUrl(), permission.getMethod()))
        .collect(Collectors.toList());
    user.setGrantedAuthorities(grantedAuthorities);
    return user;
  }
}