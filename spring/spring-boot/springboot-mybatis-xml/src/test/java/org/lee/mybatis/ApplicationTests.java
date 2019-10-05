package org.lee.mybatis;

import com.github.pagehelper.PageHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lee.mybatis.mapper.InstituteMapper;
import org.lee.mybatis.model.Institute;
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

  public static void setLOGGER(Logger LOGGER) {
    ApplicationTests.LOGGER = LOGGER;
  }

  /**
   * 保存单条记录
   *
   * @throws Exception
   */
  @Test
  public void testSave() throws Exception {
    instituteMapper.save(new Institute().withName("建筑学院"));
  }

  /**
   * 批量保存
   *
   * @throws Exception
   */
  @Test
  public void testSaveBatch() throws Exception {
    List<Institute> list = new ArrayList<>();
    list.add(new Institute().withName("数学学院"));
    list.add(new Institute().withName("物理学院"));
    list.add(new Institute().withName("机械学院"));
    list.add(new Institute().withName("医药学院"));
    instituteMapper.saveBatch(list);
    list.stream().forEach(System.out::println);
  }

  /**
   * 根据ID查询单条记录
   *
   * @throws Exception
   */
  @Test
  public void testFindById() throws Exception {
    Institute institute = instituteMapper.findById(1);
    LOGGER.info("查询结果: {}", institute);
  }

  /**
   * 根据ID集合批量查询记录
   *
   * @throws Exception
   */
  @Test
  public void testFindByIds() throws Exception {
    List<Institute> institutes = instituteMapper.findByIds(Arrays.asList(1, 2, 3, 4, 5));
    institutes.stream().forEach(institute -> LOGGER.info("查询结果: {}", institute));
  }

  /**
   * 查询所有记录
   *
   * @throws Exception
   */
  @Test
  public void testFindAll() throws Exception {
    List<Institute> institutes = instituteMapper.findAll();
    institutes.stream().forEach(institute -> LOGGER.info("查询结果: {}", institute));
  }

  /**
   * 查询分页记录
   *
   * @throws Exception
   */
  @Test
  public void testFindAllByPage() throws Exception {
    PageHelper.startPage(1, 5);
    List<Institute> institutes = instituteMapper.findAll();
    institutes.stream().forEach(institute -> LOGGER.info("查询结果: {}", institute));
  }


  /**
   * 更新单条记录
   *
   * @throws Exception
   */
  @Test
  public void testUpdate() throws Exception {
    instituteMapper.update(new Institute().withId(1).withName("通信学院"));
    Institute institute = instituteMapper.findById(1);
    LOGGER.info("查询结果: {}", institute);
  }

  /**
   * 批量更新数据
   *
   * @throws Exception
   */
  @Test
  public void testUpdateBatch() throws Exception {
    instituteMapper.updateBatch(Arrays.asList(
      new Institute().withId(1).withName("国教学院"),
      new Institute().withId(2).withName("国教学院"),
      new Institute().withId(3).withName("国教学院"),
      new Institute().withId(4).withName("国教学院"),
      new Institute().withId(5).withName("国教学院")
    ));
    List<Institute> institutes = instituteMapper.findByIds(Arrays.asList(1, 2, 3, 4, 5));
    institutes.stream().forEach(institute -> LOGGER.info("查询结果: {}", institute));
  }

  /**
   * 根据ID删除数据
   *
   * @throws Exception
   */
  @Test
  public void testDeleteById() throws Exception {
    instituteMapper.deleteById(56);
  }

  /**
   * 根据ID集合批量删除数据
   *
   * @throws Exception
   */
  @Test
  public void testDeleteByIds() throws Exception {
    instituteMapper.deleteByIds(Arrays.asList(53, 54, 55));
  }

}
