package org.lee.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.lee.mybatis.model.Institute;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface InstituteMapper {

  Institute findById(Integer id);

  List<Institute> findByIds(List<Integer> list);

  List<Institute> findAll();

  int deleteById(Integer id);

  int deleteByIds(List<Integer> list);

  int save(Institute record);

  int saveBatch(List<Institute> list);

  int update(Institute record);

  int updateBatch(List<Institute> list);
}
