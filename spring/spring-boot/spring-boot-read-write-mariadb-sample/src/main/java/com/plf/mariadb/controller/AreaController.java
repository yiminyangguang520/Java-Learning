package com.plf.mariadb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.plf.mariadb.bean.Area;
import com.plf.mariadb.service.master.AreaMasterService;
import com.plf.mariadb.service.slave.AreaSlaveService;

@RestController
@RequestMapping("area")
public class AreaController {

	@Autowired
	private AreaMasterService areaMasterService;
	
	@Autowired
	private AreaSlaveService areaSlaveService;
	
	@GetMapping("insert")
	public Area insertArea(Area area) {
		areaMasterService.InsertArea(area);
		return area;
	}
	
	@GetMapping("get/{id}")
	public Area getAreaById(@PathVariable("id") Integer id) {
		return areaSlaveService.findById(id);
	}
	
	@GetMapping("page/{pagenum}/{pagesize}")
	public PageInfo<Area> getAllByPage(@PathVariable("pagenum") Integer pagenum,@PathVariable("pagesize") Integer pagesize){
		return areaSlaveService.findAllByPage(pagenum, pagesize);
	}
}
