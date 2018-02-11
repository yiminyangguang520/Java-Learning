package com.zhisheng.dao;

import com.zhisheng.model.Score;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 10412 on 2017/8/9.
 */
@RunWith(SpringJUnit4ClassRunner.class) // 使用Springtest测试框架
@ContextConfiguration("/spring/spring-*.xml") // 加载配置
public class ScoreDaoTest {

  @Autowired
  ScoreDao scoreDao;

  /**
   * 测试ScoreDao中的insertDao方法
   */
  @Test
  public void testAddScore() {
    Score score = new Score();
    score.setChangeType("充钱钱");
    score.setScore(10);
    score.setCreate_time(new Date());
    int insert = scoreDao.insertScore(score);
    System.out.println("insert-------------------------- :" + insert);
  }
}
