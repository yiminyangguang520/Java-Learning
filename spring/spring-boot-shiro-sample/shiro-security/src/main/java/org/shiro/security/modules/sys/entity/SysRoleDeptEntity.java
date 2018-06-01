package org.shiro.security.modules.sys.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:08:35
 * 类说明：角色与部门对应关系
 */
@TableName("sys_role_dept")
public class SysRoleDeptEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  @TableId
  private Long id;

  /**
   * 角色ID
   */
  private Long roleId;

  /**
   * 部门ID
   */
  private Long deptId;

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

  /**
   * 设置：部门ID
   *
   * @param deptId 部门ID
   */
  public void setDeptId(Long deptId) {
    this.deptId = deptId;
  }

  /**
   * 获取：部门ID
   *
   * @return Long
   */
  public Long getDeptId() {
    return deptId;
  }

}
