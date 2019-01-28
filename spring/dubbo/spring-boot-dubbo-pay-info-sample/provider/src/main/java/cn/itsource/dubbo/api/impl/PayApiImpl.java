package cn.itsource.dubbo.api.impl;

import cn.itsource.dubbo.api.PayApi;
import cn.itsource.dubbo.domain.PayInfo;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author litz-a
 */
@Service(
    version = "${demo.service.version}",
    application = "${dubbo.application.id}",
    protocol = "${dubbo.protocol.id}",
    registry = "${dubbo.registry.id}"
)
public class PayApiImpl implements PayApi {

  @Override
  public PayInfo getPayInfo(String uuid) {
    // 构造数据
    PayInfo payInfo = new PayInfo();
    payInfo.setId(6666L);
    payInfo.setMethod("支付宝");
    payInfo.setStatus(1);
    payInfo.setUserName(uuid);
    return payInfo;
  }
}