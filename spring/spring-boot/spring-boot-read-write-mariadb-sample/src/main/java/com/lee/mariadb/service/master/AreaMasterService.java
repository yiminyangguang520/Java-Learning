package com.lee.mariadb.service.master;

import com.lee.mariadb.bean.Area;
import com.lee.mariadb.mapper.master.AreaMasterMapper;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bruce
 */
@Service
public class AreaMasterService {

  @Resource
  private AreaMasterMapper areaMasterMapper;

  @Transactional(rollbackFor = Exception.class)
  public void InsertArea(Area area) {
    areaMasterMapper.InsertArea(area);
  }
}
