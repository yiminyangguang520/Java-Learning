package com.zhisheng.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author min
 * Created by 10412 on 2017/8/9.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  /**
   * 用户id
   */
  private Long id;

  /**
   * 账号
   */
  private String account;

  /**
   * 头像
   */
  private String avatar;

  /**
   * 充值总额
   */
  private Long pay_money;

  /**
   * 国家
   */
  private String country;

  /**
   * 用户创建时间
   */
  private Date create_time;

  /**
   * 积分
   */
  private Long score;

  /**
   * 所在纬度
   */
  private Double latitude;

  /**
   * 所在经度
   */
  private Double longitude;

  /**
   * 省份
   */
  private String province;

  /**
   * 用户性别，F:女，M:男
   */
  private String sex;

  /**
   * 密码
   */
  private String password;

  /**
   * 排名
   */
//  private Integer rank;
}
