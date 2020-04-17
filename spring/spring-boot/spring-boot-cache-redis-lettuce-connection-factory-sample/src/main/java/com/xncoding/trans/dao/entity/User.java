package com.xncoding.trans.dao.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author min
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_user")
public class User extends Model<User> {

  /**
   * 主键ID
   */
  @TableId(value = "id", type = IdType.INPUT)
  private Integer id;

  private String username;

  private String password;

  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}
