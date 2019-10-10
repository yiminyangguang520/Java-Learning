package org.lee.mybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lee.mybatis.mapper.InstituteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

  private static Logger LOGGER = LoggerFactory.getLogger(ApplicationTests.class);

  private InstituteMapper instituteMapper;

  @Autowired
  public void setInstituteMapper(InstituteMapper instituteMapper) {
    this.instituteMapper = instituteMapper;
  }

  @Test
  public void testStartPage() throws Exception {
    instituteMapper.findAll().stream().forEach(System.out::println);
  }


}
