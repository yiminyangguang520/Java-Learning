package com.us.example.security;

import com.us.example.dao.UserDao;
import com.us.example.domain.SysRole;
import com.us.example.domain.SysUser;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 自定义UserDetailsService 接口
 * @author yangyibo
 * @date 17/1/18
 */
@Service
public class CustomUserService implements UserDetailsService {

  @Autowired
  private UserDao userDao;

  /**
   * 重写loadUserByUsername 方法获得 userdetails 类型用户
   * @param username
   * @return
   */
  @Override
  public UserDetails loadUserByUsername(String username) {
    SysUser user = userDao.findByUserName(username);

    if (user == null) {
      throw new UsernameNotFoundException("用户名不存在");
    }

    //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
    List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
        .map(sysRole -> new SimpleGrantedAuthority(sysRole.getName()))
        .collect(Collectors.toList());

    return new User(user.getUsername(), user.getPassword(), authorities);
  }

}
