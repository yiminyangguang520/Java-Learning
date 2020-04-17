package com.luoshupeng.multidatasource;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author min
 * Created by luoshupeng on 2018-03-20 9:58
 */
public class ServletInitializer extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(SpringBoot2MultiDataSourceApplication.class);
  }
}
