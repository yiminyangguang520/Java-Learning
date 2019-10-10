package org.lee.mybatis.page.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.lee.mybatis.page.model.Institute;
import org.springframework.stereotype.Repository;

/**
 * @author bruce
 */
@Mapper
@Repository
public interface InstituteMapper {

  int deleteById(Integer id);

  int save(Institute record);

  Institute findById(Integer id);

  int update(Institute record);

  List<Institute> findAll();

  List<Institute> findByIds(List<Integer> list);

  int deleteByIds(List<Integer> list);

  int saveBatch(List<Institute> list);

  int updateBatch(List<Institute> list);
}
