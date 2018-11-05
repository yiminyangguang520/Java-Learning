package com.lee.mybatis.controller;

import com.lee.mybatis.dao.RoleMapper;
import com.lee.mybatis.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RestController
public class RoleController {

  @Autowired
  private RoleMapper roleMapper;

  @GetMapping(value = "role/{id}")
  Role getRoleById(@PathVariable("id") Integer id) {
    return roleMapper.getRoles(id);
  }

}
