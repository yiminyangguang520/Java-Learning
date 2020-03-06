package com.plf.mariadb.mapper.master;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.plf.mariadb.bean.Area;

@Mapper
public interface AreaMasterMapper {

	@Insert("insert into area(name) values (#{name})")
	public void InsertArea(Area area);
}
