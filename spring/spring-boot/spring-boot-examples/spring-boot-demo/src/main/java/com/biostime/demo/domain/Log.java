package com.biostime.demo.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 类功能描述: 营销通管理平台日志表*
 *
 * @author 12360
 * @version 1.0
 * @createDate 2016/4/21 18:34:16
 */
@Entity
@Data
@Table(name = "MKT_MP_LOG")
public class Log{
    public static final String KEY = "MKT_MP_LOG";

    /*主键*/
    @Id
    @Column(name = "ID", nullable = false, length = 16)
    private Long id;
    /*模块*/
    @Column(name = "MODEL", nullable = false, length = 40)
    private String model;
    /*功能点*/
    @Column(name = "FUNCTION", nullable = false, length = 64)
    private String function;
    /*方法*/
    @Column(name = "METHOD", nullable = true, length = 20)
    private String method;
    /*操作结果*/
    @Column(name = "CODE", nullable = true, length = 50)
    private String code;
    /*操作日志信息*/
    @Column(name = "MESSAGE", nullable = true, length = 128)
    private String message;
    /*执行时间*/
    @Column(name = "EXECUTED_TIME", nullable = true, length = 16)
    private Long executedTime;
    /*创建日期*/
    @Column(name = "CREATED_DATE", nullable = false, length = 7)
    private Date createdDate;
    /*操作者*/
    @Column(name = "CREATED_BY", nullable = false, length = 100)
    private String createdBy;
}