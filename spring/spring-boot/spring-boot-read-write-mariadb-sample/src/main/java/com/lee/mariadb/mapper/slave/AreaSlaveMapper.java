package com.lee.mariadb.mapper.slave;

import com.lee.mariadb.bean.Area;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author bruce
 */
@Mapper
public interface AreaSlaveMapper {

  @Select("select id,name from area where id=#{id}")
  Area findById(Integer id);

  @Select("select id,name from area")
  List<Area> findAll();
}
