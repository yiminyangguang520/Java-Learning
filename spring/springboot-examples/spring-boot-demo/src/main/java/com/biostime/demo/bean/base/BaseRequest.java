package com.biostime.demo.bean.base;

import com.biostime.autodoc.annotations.AutoDocField;
import lombok.Data;

import java.io.Serializable;

/**
 * 请求Bean
 * Created by 12360 on 2015/9/27.
 */
@Data
public class BaseRequest<T> implements Serializable {
    @AutoDocField(note="seqNo",nullable = true)
    private String seqNo;
    @AutoDocField(note="系统来源",nullable = true)
    private String sourceSystem;
    @AutoDocField(note="请求参数",nullable = true)
    private T request;

    public BaseRequest(){
        super();
    }

    public BaseRequest(String seqNo, String sourceSystem) {
        super();
        this.seqNo = seqNo;
        this.sourceSystem = sourceSystem;
    }

}
