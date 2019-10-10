package org.lee.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.lee.mybatis.model.Course;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CourseMapper {

  Course findById(Integer id);

  List<Course> findByIds(List<Integer> list);

  List<Course> findAll();

  int deleteById(Integer id);

  int deleteByIds(List<Integer> list);

  int save(Course record);

  int saveBatch(List<Course> list);

  int update(Course record);

  int updateBatch(List<Course> list);
}
