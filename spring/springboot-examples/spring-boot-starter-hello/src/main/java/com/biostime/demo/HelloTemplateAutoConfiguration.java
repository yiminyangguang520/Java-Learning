package com.biostime.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Function:
 * <P> 版权所有 ©2013 Biostime Inc.. All Rights Reserved
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * User: 12360
 * Date: 2016/9/8
 * Time: 11:20
 */
@Configuration
@EnableConfigurationProperties(HelloTemplateProperties.class)
@ConditionalOnClass(HelloTemplate.class)
@ConditionalOnProperty(prefix = "hello",value = "enabled",matchIfMissing = true)
public class HelloTemplateAutoConfiguration {

    @Autowired
    private HelloTemplateProperties helloTemplateProperties;

    @Bean
    @ConditionalOnMissingBean(HelloTemplate.class)
    public HelloTemplate helloTemplate(){
        HelloTemplate helloTemplate = new HelloTemplate();
        helloTemplate.setMsg(helloTemplateProperties.getMsg());
        return helloTemplate;
    }

}
