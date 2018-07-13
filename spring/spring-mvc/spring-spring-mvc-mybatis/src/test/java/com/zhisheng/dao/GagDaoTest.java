package com.zhisheng.dao;

import com.zhisheng.model.Gag;
import java.util.Calendar;
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
public class GagDaoTest {

  @Autowired
  GagDao gagDao;

  @Test
  public void testInsertGag() {
    Gag gag = new Gag();
    Calendar c = Calendar.getInstance();
    gag.setCreate_time(c.getTime());
    c.add(Calendar.DAY_OF_MONTH, 1);
    gag.setGag_time(c.getTime());
    gagDao.insertGag(gag);
  }
}
