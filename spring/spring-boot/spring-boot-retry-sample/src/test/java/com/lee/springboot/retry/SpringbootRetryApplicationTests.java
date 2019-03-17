package com.lee.springboot.retry;

import com.lee.springboot.retry.serivce.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRetryApplicationTests {

  @Autowired
  private PayService payService;

  @Test
  public void payTest() throws Exception {
    int store = payService.minGoodsnum(-1);
    System.out.println("库存为：" + store);
  }

}
