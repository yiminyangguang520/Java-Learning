package com.lee.order.util;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author ChengJianSheng
 * @date 2019-02-17
 */
@Component
public class EnvironmentUtils implements EnvironmentAware {

  private Environment environment;

  @Override
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }

  public String getActiveProfile() {
    if (environment.getActiveProfiles().length > 0) {
      return environment.getActiveProfiles()[0];
    }
    return environment.getDefaultProfiles()[0];
  }
}
