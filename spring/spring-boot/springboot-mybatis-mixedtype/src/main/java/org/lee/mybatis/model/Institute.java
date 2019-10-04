package org.lee.mybatis.model;

import com.alibaba.fastjson.JSONObject;
import java.io.Serializable;
import java.util.Objects;
import org.ostenant.springboot.learning.examples.mybatis.utils.JSONAttrGetter;

public class Institute implements Serializable {

  /**
   * 主键ID
   */
  private Integer id;

  /**
   * 学院名称
   */
  private String name;

  public Integer getId() {
    return id;
  }

  public Institute withId(Integer id) {
    this.setId(id);
    return this;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public Institute withName(String name) {
    this.setName(name);
    return this;
  }

  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", name=").append(name);
    sb.append("]");
    return sb.toString();
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (that == null) {
      return false;
    }
    if (getClass() != that.getClass()) {
      return false;
    }
    Institute other = (Institute) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
      && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  public static Institute fromJson(JSONObject fromJsonObj) {
    if (fromJsonObj == null || fromJsonObj.isEmpty()) {
      return null;
    }
    Institute institute = new Institute();
    institute.setId(JSONAttrGetter.getInteger(fromJsonObj, InstituteKey.ID));
    institute.setName(JSONAttrGetter.getString(fromJsonObj, InstituteKey.NAME));
    return institute;
  }

  public JSONObject toJson() {
    JSONObject toJsonObj = new JSONObject();
    toJsonObj.put(InstituteKey.ID, id);
    toJsonObj.put(InstituteKey.NAME, name);
    return toJsonObj;
  }

  public static final class InstituteKey {

    /**
     * 主键ID
     */
    public static final String ID = "id";

    /**
     * 学院名称
     */
    public static final String NAME = "name";
  }
}
