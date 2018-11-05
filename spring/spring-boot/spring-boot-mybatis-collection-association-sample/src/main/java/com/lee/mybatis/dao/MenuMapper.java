package com.lee.mybatis.dao;

import com.lee.mybatis.model.Menu;
import com.lee.mybatis.model.MenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuMapper {

  long countByExample(MenuExample example);

  int deleteByExample(MenuExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(Menu record);

  int insertSelective(Menu record);

  List<Menu> selectByExample(MenuExample example);

  Menu selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

  int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

  int updateByPrimaryKeySelective(Menu record);

  int updateByPrimaryKey(Menu record);

  List<Menu> getMenusByRoleId(Integer roleId);
}