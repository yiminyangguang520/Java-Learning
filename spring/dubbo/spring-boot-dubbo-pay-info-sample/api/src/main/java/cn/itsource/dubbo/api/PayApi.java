package cn.itsource.dubbo.api;

import cn.itsource.dubbo.domain.PayInfo;

/**
 * @author min
 */
public interface PayApi {

  /**
   * 根据订单id获取支付消息
   * @param uuid
   * @return
   */
  PayInfo getPayInfo(String uuid);

}