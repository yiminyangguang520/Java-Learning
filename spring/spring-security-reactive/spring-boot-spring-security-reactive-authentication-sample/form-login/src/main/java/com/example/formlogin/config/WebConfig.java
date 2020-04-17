package com.example.formlogin.config;

import org.springframework.boot.web.reactive.result.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @author min
 */
@Configuration
public class WebConfig implements WebFluxConfigurer {

  private final MustacheViewResolver resolver;

  /**
   * The resolver is provided by MustacheAutoConfiguration class
   * @param resolver
   */
  WebConfig(MustacheViewResolver resolver) {
    this.resolver = resolver;
  }

  /**
   * order matters; cache will find first and render.
   * @param registry
   */
  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
    registry.viewResolver(resolver);
  }
}
