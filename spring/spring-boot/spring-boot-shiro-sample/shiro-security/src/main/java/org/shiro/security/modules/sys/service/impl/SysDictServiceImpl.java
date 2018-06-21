package org.shiro.security.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.shiro.common.utils.PageUtils;
import org.shiro.security.common.utils.Query;
import org.shiro.security.modules.sys.dao.SysDictDao;
import org.shiro.security.modules.sys.entity.SysDictEntity;
import org.shiro.security.modules.sys.service.SysDictService;
import org.springframework.stereotype.Service;


@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDictEntity> implements SysDictService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    String name = (String) params.get("name");

    Page<SysDictEntity> page = this.selectPage(
        new Query<SysDictEntity>(params).getPage(),
        new EntityWrapper<SysDictEntity>()
            .like(StringUtils.isNotBlank(name), "name", name)
    );

    return new PageUtils(page);
  }

}
