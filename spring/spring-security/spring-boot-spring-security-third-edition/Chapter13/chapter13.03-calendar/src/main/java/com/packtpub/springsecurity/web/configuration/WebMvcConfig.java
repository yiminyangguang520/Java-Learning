package com.packtpub.springsecurity.web.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 * Here we leverage Spring's {@link EnableWebMvc} support. This allows more powerful configuration but still be concise about it. Note that this class is loaded via the
 * WebAppInitializer
 * </p>
 *
 * @author Mick Knutson
 * @since chapter 01.00
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
    "com.packtpub.springsecurity.web.access.expression",
    "com.packtpub.springsecurity.web.access.intercept",
    "com.packtpub.springsecurity.web.configuration",
    "com.packtpub.springsecurity.web.controllers",
    "com.packtpub.springsecurity.web.model"
})
public class WebMvcConfig implements WebMvcConfigurer {

  private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
      "classpath:/META-INF/resources/", "classpath:/resources/",
      "classpath:/static/", "classpath:/public/"};

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**")
        .addResourceLocations("/resources/")
        //Set to 0 in order to send cache headers that prevent caching
        .setCachePeriod(0)
    ;

    // Add WebJars for Bootstrap & jQuery
    if (!registry.hasMappingForPattern("/webjars/**")) {
      registry.addResourceHandler("/webjars/**").addResourceLocations(
          "classpath:/META-INF/resources/webjars/").resourceChain(true);
    }

    if (!registry.hasMappingForPattern("/**")) {
      registry.addResourceHandler("/**").addResourceLocations(
          CLASSPATH_RESOURCE_LOCATIONS);

    }
  }

  @Override
  public void addViewControllers(final ViewControllerRegistry registry) {
    registry.addViewController("/login/form")
        .setViewName("login");
    registry.addViewController("/errors/403")
        .setViewName("/errors/403");

    registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
  }

//    @Description("JPA based IP Away Remember me token repository")
//    @Bean
//    public IpAwarePersistentTokenRepository ipAwarePersistentTokenRepository(RememberMeTokenRepository rmtr) {
//        return new IpAwarePersistentTokenRepository(
//                new JpaPersistentTokenRepository(rmtr)
//        );
//    }
//
//    @Description("OrderedRequestContextFilter")
//    @Bean
//    public OrderedRequestContextFilter requestContextFilter() {
//        return new OrderedRequestContextFilter();
//    }

}
