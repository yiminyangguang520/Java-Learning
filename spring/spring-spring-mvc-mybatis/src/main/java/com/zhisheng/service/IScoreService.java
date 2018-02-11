package com.zhisheng.service;

import com.zhisheng.model.Score;
import com.zhisheng.model.User;

/**
 * @author litz-a
 * Created by 10412 on 2017/8/9.
 */
public interface IScoreService {

  /**
   * 插入积分
   * @param score
   * @return
   */
  int insertScore(Score score);

  /**
   * 更新用户的积分
   * @param user
   * @param scoreCount
   */
  void updateScore(User user, int scoreCount);
}
