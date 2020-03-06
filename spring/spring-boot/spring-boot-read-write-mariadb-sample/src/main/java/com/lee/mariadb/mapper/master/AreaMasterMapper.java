package com.lee.mariadb.mapper.master;

import com.lee.mariadb.bean.Area;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author bruce
 */
@Mapper
public interface AreaMasterMapper {

  @Insert("insert into area(name) values (#{name})")
  void InsertArea(Area area);
}
