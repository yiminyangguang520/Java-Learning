package com.antsoft.model;

import com.antsoft.database.BaseEntity;
import javax.persistence.Table;

/**
 *
 * @author Jason
 * @date 2017/3/6
 */
@Table(name = "t_sys_dic_type")
public class DicType extends BaseEntity {

  String code;

  String name;

  Integer status;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
