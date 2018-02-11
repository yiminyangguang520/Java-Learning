package com.zhisheng.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author litz-a
 * Created by 10412 on 2017/8/9.
 * 积分
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {

  private Long id;
  /**
   * 积分变化类型
   */
  private String changeType;

  /**
   * 创建时间
   */
  private Date create_time;

  /**
   * 积分变化数
   */
  private Integer score;

  /**
   * 爱豆变化者
   */
  private User user;
}
