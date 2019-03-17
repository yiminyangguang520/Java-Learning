package cn.msgcode.provider;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.listener.AbstractSharedListener;
import org.springframework.stereotype.Component;

@Component
public class ConfigListener extends AbstractSharedListener {

  @Override
  public void innerReceive(String dataId, String group, String configInfo) {
    System.out.println(String.format("dataId:%s  group:%s configInfo:%s", dataId, group, configInfo));
  }

  @NacosInjected
  @NacosConfigListener(groupId = "test-group", dataId = "nacos-provider.yml")
  public void handle(String dataId, String group, String configInfo) {
    System.out.println(String.format("dataId:%s  group:%s configInfo:%s", dataId, group, configInfo));
  }
}
