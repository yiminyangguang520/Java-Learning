package com.biostime.demo.bean;

import com.biostime.autodoc.annotations.AutoDocField;
import lombok.Data;

import java.io.Serializable;

/**
 * Function:LogIdBean
 * <P> 版权所有 ©2013 Biostime Inc.. All Rights Reserved
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * User: 12360
 * Date: 2016/11/11
 * Time: 14:33
 */
@Data
public class LogIdBean implements Serializable {

    @AutoDocField(note="主键Id",nullable = true)
    private Long id;
}
