/**
 *
 */
package com.devglan.service.impl;

import com.devglan.dao.UserDao;
import com.devglan.model.UserDetails;
import com.devglan.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author min
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Override
  public List<UserDetails> getUserDetails() {
    return userDao.getUserDetails();

  }

}
