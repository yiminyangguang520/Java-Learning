package com.us.example.security;

import com.us.example.dao.UserDao;
import com.us.example.domain.Role;
import com.us.example.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author min
 */
@Service
public class CustomUserService implements UserDetailsService {

  private static final Logger logger = LoggerFactory.getLogger(CustomUserService.class);

  @Autowired
  private UserDao userDao;

  /**
   * 重写loadUserByUsername 方法获得 userdetails 类型用户
   * @param username
   * @return
   */
  @Override
  public UserDetails loadUserByUsername(String username) {
    User user = userDao.findByUserName(username);

    if (user == null) {
      throw new UsernameNotFoundException("用户名不存在");
    }

    //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
    List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());
    logger.info("loadUserByUsername: " + user);
    // 用于登录时 @AuthenticationPrincipal 标签取值
    user.setGrantedAuthorities(authorities);
    return user;
  }

}
