package com.lee.mybatis;

import com.lee.mybatis.dao.RoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {

  @Autowired
  private RoleMapper roleMapper;

  @Test
  public void contextLoads() {
    System.out.println(roleMapper.getRoles(1));
  }

}
