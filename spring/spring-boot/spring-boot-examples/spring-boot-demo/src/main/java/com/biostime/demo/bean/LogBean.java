package com.biostime.demo.bean;

import com.biostime.autodoc.annotations.AutoDocField;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Function:LogBean
 * <P> 版权所有 ©2013 Biostime Inc.. All Rights Reserved
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * User: 12360
 * Date: 2016/11/11
 * Time: 14:33
 */
@Data
public class LogBean implements Serializable {

    @AutoDocField(note="主键Id", nullable = false, length = 16)
    private Long id;

    @AutoDocField(note= "模块", nullable = false, length = 40)
    private String model;

    @AutoDocField(note= "功能点", nullable = false, length = 64)
    private String function;

    @AutoDocField(note= "方法", nullable = true, length = 20)
    private String method;

    @AutoDocField(note= "操作结果", nullable = true, length = 50)
    private String code;

    @AutoDocField(note= "操作日志信息", nullable = true, length = 128)
    private String message;

    @AutoDocField(note= "执行时间", nullable = true, length = 16)
    private Long executedTime;

    @AutoDocField(note= "创建日期", nullable = false, length = 7)
    private Date createdDate;

    @AutoDocField(note= "操作者", nullable = false, length = 100)
    private String createdBy;
}
