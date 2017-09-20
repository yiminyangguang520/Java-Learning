package com.kfit.test.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.kfit.test.bean.Demo;
import com.kfit.test.dao.DemoRepository;

/**
 * 提供Demo服务类.
 *
 * @author Administrator
 */
@Service
public class DemoService {

  @Resource
  private DemoRepository demoRepository;

  public void save(Demo demo) {
    demoRepository.save(demo);
  }
}
