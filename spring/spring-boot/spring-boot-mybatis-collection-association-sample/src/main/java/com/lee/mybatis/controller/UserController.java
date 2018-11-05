package com.lee.mybatis.controller;

import com.lee.mybatis.dao.UserMapper;
import com.lee.mybatis.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RestController
public class UserController {

  @Autowired
  private UserMapper userMapper;

  @GetMapping(value = "user")
  List<User> getRoleById() {
    return userMapper.getAllUsers();
  }
}
