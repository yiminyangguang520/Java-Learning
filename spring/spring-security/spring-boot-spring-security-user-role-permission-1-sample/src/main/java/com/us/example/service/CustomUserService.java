package com.us.example.service;

import com.us.example.dao.PermissionDao;
import com.us.example.dao.UserDao;
import com.us.example.domain.Permission;
import com.us.example.domain.SysUser;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author min
 */
@Service
public class CustomUserService implements UserDetailsService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserDao userDao;

  @Autowired
  private PermissionDao permissionDao;

  @Override
  public UserDetails loadUserByUsername(String username) {
    SysUser user = userDao.findByUserName(username);

    if (user == null) {
      throw new UsernameNotFoundException("admin: " + username + " do not exist!");
    }

    List<Permission> permissions = permissionDao.findByAdminUserId(user.getId());
    List<GrantedAuthority> grantedAuthorities = permissions.stream()
        .filter(permission -> StringUtils.isNotEmpty(permission.getName()))
        .map(permission -> new CustomGrantedAuthority(permission.getUrl(), permission.getMethod()))
        .collect(Collectors.toList());
    return new User(user.getUsername(), passwordEncoder.encode(user.getPassword()), grantedAuthorities);
  }
}
