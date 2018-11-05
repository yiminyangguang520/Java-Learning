package com.lee.authlogin.service;

import com.lee.authlogin.domain.User;
import com.lee.authlogin.repository.UserRepository;
import com.lee.authlogin.valueobject.UserView;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author litz-a
 */
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Transactional(rollbackFor = Exception.class)
  public UserView getUserByUserName(String userName) {

    UserView userView = new UserView();
    User user = userRepository.findByUserName(userName);
    userView.setUserName(user.getUserName());
    userView.setUserDesc(user.getUserDescription());
    List<String> roleCodes = new ArrayList<>();
    user.getRoles().stream()
        .forEach(role -> roleCodes.add(role.getRoleCode()));
    userView.setRoleCodes(roleCodes);
    return userView;
  }
}
