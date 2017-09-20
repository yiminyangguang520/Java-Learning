package com.kfit.base.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 访问myres根目录下的fengjing.jpg 的URL为 http://localhost:8080/fengjing.jpg （/** 会覆盖系统默认的配置）
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/myres/").addResourceLocations("classpath:/static/");

        //访问地址：http://127.0.0.1:8080/myres/test1.jpg
        registry.addResourceHandler("/myres/**").addResourceLocations("classpath:/myres/");


        //访问地址：http://127.0.0.1:8080/api_files/test1.jpg
        // 可以直接使用addResourceLocations 指定磁盘绝对路径，同样可以配置多个位置，注意路径写法需要加上file:
        registry.addResourceHandler("/api_files/**").addResourceLocations("file:D:/data/api_files/");

        super.addResourceHandlers(registry);
    }

}
