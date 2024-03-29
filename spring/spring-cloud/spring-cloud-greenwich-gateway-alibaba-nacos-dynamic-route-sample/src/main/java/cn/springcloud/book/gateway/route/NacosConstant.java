package cn.springcloud.book.gateway.route;

import com.alibaba.nacos.api.PropertyKeyConst;

/**
 * @author min
 */
public class NacosConstant extends PropertyKeyConst {

  public static final String NACOS_SERVER_ADDR = "nacos.server-addr";

  public static final long DEFAULT_TIMEOUT = 30000;
}