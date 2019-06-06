package com.example.webfluxsessiondemo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangxing
 * @create 2019/5/15
 */
@Data
public class User implements Serializable {

  private String id;

  private String userName;

  private String nickName;

}