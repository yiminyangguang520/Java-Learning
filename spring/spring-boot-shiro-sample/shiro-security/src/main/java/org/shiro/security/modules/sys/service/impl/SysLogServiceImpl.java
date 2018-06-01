package org.shiro.security.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.shiro.common.utils.PageUtils;
import org.shiro.security.common.utils.Query;
import org.shiro.security.modules.sys.dao.SysLogDao;
import org.shiro.security.modules.sys.entity.SysLogEntity;
import org.shiro.security.modules.sys.service.SysLogService;
import org.springframework.stereotype.Service;


@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    String key = (String) params.get("key");

    Page<SysLogEntity> page = this.selectPage(
        new Query<SysLogEntity>(params).getPage(),
        new EntityWrapper<SysLogEntity>().like(StringUtils.isNotBlank(key), "username", key)
    );

    return new PageUtils(page);
  }
}
