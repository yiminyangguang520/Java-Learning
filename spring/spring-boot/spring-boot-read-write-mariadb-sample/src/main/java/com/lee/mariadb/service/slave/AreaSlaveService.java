package com.lee.mariadb.service.slave;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lee.mariadb.bean.Area;
import com.lee.mariadb.mapper.slave.AreaSlaveMapper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author bruce
 */
@Service
public class AreaSlaveService {

  @Resource
  private AreaSlaveMapper areaSlaveMapper;

  public Area findById(Integer id) {
    return areaSlaveMapper.findById(id);
  }

  public PageInfo<Area> findAllByPage(Integer pageNum, Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    List<Area> list = areaSlaveMapper.findAll();
    return PageInfo.of(list);
  }

}
