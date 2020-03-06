package com.plf.mariadb.service.slave;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plf.mariadb.bean.Area;
import com.plf.mariadb.mapper.slave.AreaSlaveMapper;

@Service
public class AreaSlaveService {

	@Autowired
	private AreaSlaveMapper areaSlaveMapper;
	
	public Area findById(Integer id) {
		return areaSlaveMapper.findById(id);
	}
	
	public PageInfo<Area> findAllByPage(Integer pageNum,Integer pageSize){
		PageHelper.startPage(pageNum, pageSize);
		List<Area> list = areaSlaveMapper.findAll();
		return PageInfo.of(list);
	}
	
}
