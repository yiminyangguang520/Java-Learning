package org.lee.mybatis.service.impl;

import java.util.List;
import org.lee.mybatis.mapper.InstituteMapper;
import org.lee.mybatis.model.Institute;
import org.lee.mybatis.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author bruce
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InstituteServiceImpl implements InstituteService {

  @Autowired
  private InstituteMapper instituteMapper;

  @Override
  public int deleteById(Integer id) {
    return instituteMapper.deleteById(id);
  }

  @Override
  public int save(Institute record) {
    return instituteMapper.save(record);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Institute> findAll() {
    return instituteMapper.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Institute findById(Integer id) {
    return instituteMapper.findById(id);
  }

  @Override
  public int update(Institute record) {
    return instituteMapper.update(record);
  }

}
