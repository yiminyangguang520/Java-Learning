package com.zhisheng.service.impl;

import com.zhisheng.dao.ScoreDao;
import com.zhisheng.dao.UserDao;
import com.zhisheng.model.Score;
import com.zhisheng.model.User;
import com.zhisheng.service.IJedisClientService;
import com.zhisheng.service.IScoreService;
import com.zhisheng.util.JsonUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author min
 * Created by 10412 on 2017/8/9.
 */
@Service
public class ScoreServiceImpl implements IScoreService {

  @Autowired
  private ScoreDao scoreDao;

  @Autowired
  private UserDao userDao;

  @Autowired
  private IJedisClientService jedisClientService;

  /**
   * 插入积分
   */
  @Override
  public int insertScore(Score score) {
    return scoreDao.insertScore(score);
  }

  /**
   * 更新用户的积分
   */
  @Transactional(rollbackFor = Exception.class)
  @Override
  public void updateScore(User user, int scoreCount) {
    User userFind = new User();
//        Score score = new Score();
//        score.setChangeType("做任务");
//        score.setUser(user);
//        score.setCreate_time(new Date());
//        score.setScore(scoreCount);

    scoreDao.updateScore(user.getId(), scoreCount);
    userDao.updateScore(user.getId(), scoreCount);
    Set<String> set = jedisClientService.getTopLast("Toptest", 0, 5);
    Iterator<String> iterator = set.iterator();
    List<User> userList = new ArrayList<>();
    while (iterator.hasNext()) {
      User user1 = JsonUtils.jsonObjectToUser(iterator.next());
      System.out.println(" user = " + user.toString());
      userList.add(user1);
      if (user1.getAccount().equals(user.getAccount())) {
        userFind = user1;
      }
    }
    //如果原本有就修改原有的
    if (userFind != null) {
      jedisClientService.zadd("Toptest", user.getScore(), userFind);
      System.out.println("修改原有的！");
      return;
    }
    //如果没有就加进去嘛，根据排行榜来区分
    if (userList.size() >= 20) {
      if (user.getScore() + scoreCount > userList.get(19).getScore()) {
        System.out.println("大于排名20名中的最后一名就丢进缓存    :");
        System.out.println("userList    :" + userList.toString());
      }
    } else {
      //排行榜人数小于20
      System.out.println("排行榜小于20人就直接丢进缓存    :");
      if (userFind != null) {
        jedisClientService.zadd("Toptest", user.getScore(), user);
      }
      System.out.println("user :  " + userFind);
    }
  }
}
