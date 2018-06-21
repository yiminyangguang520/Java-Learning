package org.shiro.security.common.config;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;

/**
 * @author yuanpb
 * @version 创建时间：2018年5月2日 上午10:06:13 类说明:重写CorsRegistration
 */
public class CustomCorsRegistration extends CorsRegistration {

  public CustomCorsRegistration(String pathPattern) {
    super(pathPattern);
  }

  @Override
  public CorsConfiguration getCorsConfiguration() {
    return super.getCorsConfiguration();
  }

}
