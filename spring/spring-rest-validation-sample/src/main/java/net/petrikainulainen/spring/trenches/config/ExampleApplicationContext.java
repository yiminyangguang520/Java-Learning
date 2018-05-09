package net.petrikainulainen.spring.trenches.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Petri Kainulainen
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
    "net.petrikainulainen.spring.trenches.comment.controller"
})
@Import({MessageContext.class, TestMessageContext.class})
@PropertySource("classpath:application.properties")
public class ExampleApplicationContext implements WebMvcConfigurer {

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  @Bean
  public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();

    properties.setLocation(new ClassPathResource("application.properties"));
    properties.setIgnoreResourceNotFound(false);

    return properties;
  }
}
