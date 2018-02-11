package com.zhisheng.dao;

import com.zhisheng.model.Score;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 10412 on 2017/8/9.
 */

public interface ScoreDao {

  /**
   * 插入积分
   */
  int insertScore(Score score);

  /**
   * 通过用户id更改积分
   */
  void updateScore(@Param("id") long id, @Param("scoreCount") Integer scoreCount);
}
