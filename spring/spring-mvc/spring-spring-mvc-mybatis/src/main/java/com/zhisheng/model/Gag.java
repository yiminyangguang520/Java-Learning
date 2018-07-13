package com.zhisheng.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author litz-a
 * Created by 10412 on 2017/8/9.
 * 禁言
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gag {

  private Long id;
  /**
   * 创建时间
   */
  private Date create_time;

  /**
   * 禁言到某个时间
   */
  private Date gag_time;

  /**
   * 被禁言者
   */
  private User user;

}
