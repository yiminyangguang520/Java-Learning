package com.lee.validation.dto;

import javax.validation.constraints.NotNull;

/**
 * 用户更新 DTO
 * @author min
 */
public class UserUpdateDTO {

  /**
   * 用户编号
   */
  @NotNull(message = "{UserUpdateDTO.id.NotNull}")
  private Integer id;

  public Integer getId() {
    return id;
  }

  public UserUpdateDTO setId(Integer id) {
    this.id = id;
    return this;
  }

}
