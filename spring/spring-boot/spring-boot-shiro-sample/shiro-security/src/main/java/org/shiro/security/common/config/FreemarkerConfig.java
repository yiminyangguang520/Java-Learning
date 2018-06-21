package org.shiro.security.common.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.shiro.security.modules.sys.shiro.ShiroTag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:43:39
 * 类说明： Freemarker配置
 */
@Configuration
public class FreemarkerConfig {

  @Bean
  public FreeMarkerConfigurer freeMarkerConfigurer(ShiroTag shiroTag) {
    FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
    configurer.setTemplateLoaderPath("classpath:/templates");
    Map<String, Object> variables = new HashMap<>(1);
    variables.put("shiro", shiroTag);
    configurer.setFreemarkerVariables(variables);

    Properties settings = new Properties();
    settings.setProperty("default_encoding", "utf-8");
    settings.setProperty("number_format", "0.##");
    configurer.setFreemarkerSettings(settings);
    return configurer;
  }

}
