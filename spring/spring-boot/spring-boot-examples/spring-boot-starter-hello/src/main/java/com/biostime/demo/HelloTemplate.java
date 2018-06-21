package com.biostime.demo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Function:
 * <P> 版权所有 ©2013 Biostime Inc.. All Rights Reserved
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * User: 12360
 * Date: 2016/9/8
 * Time: 11:16
 */
@Data
public class HelloTemplate {

    private String msg;

    public String sayHello(){
        return "Hello "+ msg;
    }
}
