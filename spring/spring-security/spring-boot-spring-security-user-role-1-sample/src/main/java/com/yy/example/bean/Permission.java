package com.yy.example.bean;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author min
 */
@Getter
@Setter
@ToString
public class Permission {

  private Integer id;
  private String name;
  private String permissionUrl;
  private String method;
  private String description;
}