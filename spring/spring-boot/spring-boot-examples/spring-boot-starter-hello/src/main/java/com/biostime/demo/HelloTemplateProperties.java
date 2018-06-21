package com.biostime.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;
/**
 * Function:
 * <P> 版权所有 ©2013 Biostime Inc.. All Rights Reserved
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * User: 12360
 * Date: 2016/9/8
 * Time: 11:22
 */
@ConfigurationProperties(prefix = "hello")
@Data
public class HelloTemplateProperties {
    private String msg = "world";
}
