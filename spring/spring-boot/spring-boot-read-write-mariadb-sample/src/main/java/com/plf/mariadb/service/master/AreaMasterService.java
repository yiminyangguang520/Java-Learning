package com.plf.mariadb.service.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plf.mariadb.bean.Area;
import com.plf.mariadb.mapper.master.AreaMasterMapper;

@Service
public class AreaMasterService {

	@Autowired
	private AreaMasterMapper areaMasterMapper;
	
	@Transactional(rollbackFor = Exception.class)
	public void InsertArea(Area area) {
		areaMasterMapper.InsertArea(area);
	}
}
