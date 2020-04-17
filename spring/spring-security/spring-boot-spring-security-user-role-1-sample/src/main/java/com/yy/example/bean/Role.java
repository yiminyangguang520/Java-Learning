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
public class Role implements Comparable<Role> {

  private Integer id;
  private String name;
  private Integer roleLevel;
  private String description;
  private String menuItems;

  @Override
  public int compareTo(Role o) {
    if (id.equals(o.getId())) {
      return 0;
    } else if (id > o.getId()) {
      return 1;
    } else {
      return -1;
    }
  }

  @Override
  public boolean equals(Object obj) {
    // TODO Auto-generated method stub
    if (obj instanceof Role) {
      if (id.equals(((Role) obj).getId())) {
        return true;
      }
    }
    return false;
  }
}