package org.shiro.security.modules.sys.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:07:08
 * 类说明：用户与角色对应关系
 */
@TableName("sys_user_role")
public class SysUserRoleEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  @TableId
  private Long id;

  /**
   * 用户ID
   */
  private Long userId;

  /**
   * 角色ID
   */
  private Long roleId;

  /**
   * 设置：
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * 获取：
   *
   * @return Long
   */
  public Long getId() {
    return id;
  }

  /**
   * 设置：用户ID
   *
   * @param userId 用户ID
   */
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  /**
   * 获取：用户ID
   *
   * @return Long
   */
  public Long getUserId() {
    return userId;
  }

  /**
   * 设置：角色ID
   *
   * @param roleId 角色ID
   */
  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  /**
   * 获取：角色ID
   *
   * @return Long
   */
  public Long getRoleId() {
    return roleId;
  }

}
