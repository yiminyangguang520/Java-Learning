package org.shiro.security.modules.sys.service;


import com.baomidou.mybatisplus.service.IService;
import java.util.Map;
import org.shiro.common.utils.PageUtils;
import org.shiro.security.modules.sys.entity.SysLogEntity;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:59:33
 * 类说明：系统日志
 */
public interface SysLogService extends IService<SysLogEntity> {

  PageUtils queryPage(Map<String, Object> params);

}
