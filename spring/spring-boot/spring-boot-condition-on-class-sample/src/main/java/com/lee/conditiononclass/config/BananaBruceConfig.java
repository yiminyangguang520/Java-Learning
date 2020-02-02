package com.lee.conditiononclass.config;

import com.lee.conditiononclass.player.Bruce;
import com.lee.conditiononclass.fighter.Banana;
import com.lee.conditiononclass.fighter.Fighter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author bruce
 */
@Configuration
@ConditionalOnClass({Banana.class})
public class BananaBruceConfig {

  @Bean
  public Fighter banana() {
    return new Banana();
  }

  @Bean
  public Bruce bananaVan() {
    return new Bruce(banana());
  }
}
