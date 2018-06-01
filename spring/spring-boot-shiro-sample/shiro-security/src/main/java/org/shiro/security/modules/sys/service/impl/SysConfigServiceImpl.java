package org.shiro.security.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.shiro.common.exception.ShiroException;
import org.shiro.common.utils.PageUtils;
import org.shiro.security.common.utils.Query;
import org.shiro.security.datasources.DataSourceNames;
import org.shiro.security.datasources.annotation.DataSource;
import org.shiro.security.modules.sys.dao.SysConfigDao;
import org.shiro.security.modules.sys.entity.SysConfigEntity;
import org.shiro.security.modules.sys.redis.SysConfigRedis;
import org.shiro.security.modules.sys.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sysConfigService")
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfigEntity> implements SysConfigService {

  @Autowired
  private SysConfigRedis sysConfigRedis;

  @Override
  @DataSource(name = DataSourceNames.SECOND)
  public PageUtils queryPage(Map<String, Object> params) {
    String paramKey = (String) params.get("paramKey");

    Page<SysConfigEntity> page = this.selectPage(
        new Query<SysConfigEntity>(params).getPage(),
        new EntityWrapper<SysConfigEntity>()
            .like(StringUtils.isNotBlank(paramKey), "param_key", paramKey)
            .eq("status", 1)
    );

    return new PageUtils(page);
  }

  @Override
  public void save(SysConfigEntity config) {
    this.insert(config);
    sysConfigRedis.saveOrUpdate(config);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void update(SysConfigEntity config) {
    this.updateAllColumnById(config);
    sysConfigRedis.saveOrUpdate(config);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void updateValueByKey(String key, String value) {
    baseMapper.updateValueByKey(key, value);
    sysConfigRedis.delete(key);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void deleteBatch(Long[] ids) {
    for (Long id : ids) {
      SysConfigEntity config = this.selectById(id);
      sysConfigRedis.delete(config.getParamKey());
    }

    this.deleteBatchIds(Arrays.asList(ids));
  }

  @Override
  public String getValue(String key) {
    SysConfigEntity config = sysConfigRedis.get(key);
    if (config == null) {
      config = baseMapper.queryByKey(key);
      sysConfigRedis.saveOrUpdate(config);
    }

    return config == null ? null : config.getParamValue();
  }

  @Override
  public <T> T getConfigObject(String key, Class<T> clazz) {
    String value = getValue(key);
    if (StringUtils.isNotBlank(value)) {
      return new Gson().fromJson(value, clazz);
    }

    try {
      return clazz.newInstance();
    } catch (Exception e) {
      throw new ShiroException("获取参数失败");
    }
  }
}
