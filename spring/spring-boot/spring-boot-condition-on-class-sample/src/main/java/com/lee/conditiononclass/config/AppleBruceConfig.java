package com.lee.conditiononclass.config;

import com.lee.conditiononclass.player.Bruce;
import com.lee.conditiononclass.fighter.Apple;
import com.lee.conditiononclass.fighter.Fighter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ConditionalOnClass是Springboot实现自动配置的重要支撑之一。其用途是判断当前classpath下是否存在指定类，若是则将当前的配置装载入spring容器
 * @author bruce
 */
@Configuration
@ConditionalOnClass({Apple.class})
public class AppleBruceConfig {

  @Bean
  public Fighter billy() {
    return new Apple();
  }

  @Bean
  public Bruce billyVan() {
    return new Bruce(billy());
  }
}
