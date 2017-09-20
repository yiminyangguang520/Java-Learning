/*
 * %W% %E%
 *
 * Copyright (c) 2014, Biostime, Inc. All rights reserved.
 *
 */
package com.biostime.demo.bean.base;

import com.biostime.autodoc.annotations.AutoDocField;
import com.biostime.demo.util.PropUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * 响应Bean
 * Created by 12360 on 2015/9/27.
 */
@Data
public class BaseResponse<T> implements Serializable {
    @AutoDocField(note="seqNo",nullable = true)
    private String seqNo;
    @AutoDocField(note="编码",nullable = true)
    private int code = 100;
    @AutoDocField(note="编码描述",nullable = true)
    private String desc = PropUtils.getProp(100);
    @AutoDocField(note="响应值",nullable = true)
    private T response;

    public BaseResponse(String seqNo){
        this.seqNo = seqNo;
    }

    public BaseResponse(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public BaseResponse(int code, String desc,String seqNo) {
        this.code = code;
        this.desc = desc;
        this.seqNo = seqNo;
    }
}
