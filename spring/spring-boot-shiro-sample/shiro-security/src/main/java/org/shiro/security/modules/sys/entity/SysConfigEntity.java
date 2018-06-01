package org.shiro.security.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.NotBlank;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:05:56
 * 类说明：系统配置信息
 */
@TableName("sys_config")
public class SysConfigEntity {

  @TableId
  private Long id;
  @NotBlank(message = "参数名不能为空")
  private String paramKey;
  @NotBlank(message = "参数值不能为空")
  private String paramValue;
  private String remark;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getParamKey() {
    return paramKey;
  }

  public void setParamKey(String paramKey) {
    this.paramKey = paramKey;
  }

  public String getParamValue() {
    return paramValue;
  }

  public void setParamValue(String paramValue) {
    this.paramValue = paramValue;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
