package org.shiro.security.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import java.util.Map;
import org.shiro.common.utils.PageUtils;
import org.shiro.security.modules.sys.entity.SysDictEntity;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:59:57
 * 类说明：数据字典
 */
public interface SysDictService extends IService<SysDictEntity> {

  PageUtils queryPage(Map<String, Object> params);
}

