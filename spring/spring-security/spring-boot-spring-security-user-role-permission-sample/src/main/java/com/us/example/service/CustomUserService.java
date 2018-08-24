package com.us.example.service;

import com.us.example.dao.PermissionMapper;
import com.us.example.dao.UserMapper;
import com.us.example.model.Permission;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 */
@Service
public class CustomUserService implements UserDetailsService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserMapper userDao;

  @Autowired
  private PermissionMapper permissionDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    com.us.example.model.User user = userDao.findByUserName(username);

    if (user == null) {
      throw new UsernameNotFoundException("admin: " + username + " do not exist!");
    }

    List<Permission> permissions = permissionDao.findByAdminUserId(user.getId());
    //此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象
    List<GrantedAuthority> grantedAuthorities = permissions.stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getName()))
        .collect(Collectors.toList());
    return new User(user.getUsername(), passwordEncoder.encode(user.getPassword()), grantedAuthorities);
  }

}
