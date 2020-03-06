package com.lee.mariadb.controller;

import com.github.pagehelper.PageInfo;
import com.lee.mariadb.bean.Area;
import com.lee.mariadb.service.master.AreaMasterService;
import com.lee.mariadb.service.slave.AreaSlaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bruce
 */
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
  public PageInfo<Area> getAllByPage(@PathVariable("pagenum") Integer pagenum, @PathVariable("pagesize") Integer pagesize) {
    return areaSlaveService.findAllByPage(pagenum, pagesize);
  }
}
