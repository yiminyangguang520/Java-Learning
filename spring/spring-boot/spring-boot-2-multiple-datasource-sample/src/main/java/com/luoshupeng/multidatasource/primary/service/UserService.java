package com.luoshupeng.multidatasource.primary.service;

import com.luoshupeng.multidatasource.baseservice.IBaseService;
import com.luoshupeng.multidatasource.primary.entity.User;
import com.luoshupeng.multidatasource.primary.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 * Created by luoshupeng on 2018-03-20 10:26
 */
@Service
public class UserService implements IBaseService<User> {

  @Autowired
  UserRepository userRepository;

  @Override
  public List<User> list() {
    return userRepository.findAll();
  }
}
