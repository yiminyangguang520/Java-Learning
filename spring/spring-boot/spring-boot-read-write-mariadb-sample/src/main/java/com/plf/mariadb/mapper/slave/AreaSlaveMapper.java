package com.plf.mariadb.mapper.slave;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.plf.mariadb.bean.Area;

@Mapper
public interface AreaSlaveMapper {

	@Select("select id,name from area where id=#{id}")
	public Area findById(Integer id);
	
	@Select("select id,name from area")
	public List<Area> findAll();
}
