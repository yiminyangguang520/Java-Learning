//package com.packtpub.springsecurity.web.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Description;
//import org.springframework.web.servlet.ViewResolver;
//import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.thymeleaf.spring5.view.ThymeleafViewResolver;
//import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
//
//@Configuration
//public class ThymeleafConfig {
//
//  @Bean
//  @Description("Thymeleaf template resolver serving HTML 5")
//  public TemplateResolver templateResolver() {
//
//    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//
//    templateResolver.setPrefix("templates/");
//    templateResolver.setCacheable(false);
//    templateResolver.setSuffix(".html");
//    templateResolver.setTemplateMode("HTML5");
//    templateResolver.setCharacterEncoding("UTF-8");
//
//    return templateResolver;
//  }
//
//  @Bean
//  @Description("Thymeleaf template engine with Spring integration")
//  public SpringTemplateEngine templateEngine(final TemplateResolver templateResolver) throws Exception {
//    SpringTemplateEngine engine = new SpringTemplateEngine();
//    engine.setTemplateResolver(templateResolver);
//
//    engine.addDialect(new SpringSecurityDialect());
//    engine.addDialect(new LayoutDialect(new GroupingStrategy()));
//    engine.afterPropertiesSet();
//    return engine;
//  }
//
//  @Bean
//  @Description("Thymeleaf view resolver")
//  public ViewResolver viewResolver(final SpringTemplateEngine templateEngine) {
//    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//    resolver.setTemplateEngine(templateEngine);
//    resolver.setCharacterEncoding("UTF-8");
//    resolver.setCache(false);
//    resolver.setOrder(1);
//    return resolver;
//  }
//
//} // The End...