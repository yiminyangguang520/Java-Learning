package com.devglan.service.impl;

import com.devglan.dao.UserDao;
import com.devglan.model.User;
import com.devglan.service.UserService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * @author bruce
 */
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

  @Autowired
  private UserDao userDao;

  @Override
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    User user = userDao.findByUsername(userId);
    if (user == null) {
      throw new UsernameNotFoundException("Invalid username or password.");
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
  }

  private List<SimpleGrantedAuthority> getAuthority() {
    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
  }

  @Override
  public List<User> findAll() {
    List<User> list = new ArrayList<>();
    userDao.findAll().iterator().forEachRemaining(list::add);
    return list;
  }

  @Override
  public void delete(long id) {
    userDao.deleteById(id);
  }

  @Override
  public User save(User user) {
    return userDao.save(user);
  }
}
