package com.xiaoze.api.entity;

import java.io.Serializable;
import org.springframework.stereotype.Component;

/**
 * CourseType
 *
 * @author xiaoze
 * @date 2018/6/3
 */
@Component
public class CourseType implements Serializable {

  private Integer typeId;

  private String typeName;

  public Integer getTypeId() {
    return typeId;
  }

  public void setTypeId(Integer typeId) {
    this.typeId = typeId;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  @Override
  public String toString() {
    return "CourseType [typeId=" + typeId + ", typeName=" + typeName + "]";
  }

}
